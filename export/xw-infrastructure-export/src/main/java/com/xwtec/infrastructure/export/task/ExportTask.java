package com.xwtec.infrastructure.export.task;

import com.github.pagehelper.PageInfo;
import com.xwtec.infrastructure.export.config.ExportProperties;
import com.xwtec.infrastructure.export.model.ExportFormat;
import com.xwtec.infrastructure.export.model.ExportTaskInfo;
import com.xwtec.infrastructure.export.model.ExportTaskStatus;
import com.xwtec.infrastructure.export.service.ExportService;
import com.xwtec.infrastructure.export.task.export.CsvExportHandle;
import com.xwtec.infrastructure.export.task.export.ExcelExportHandle;
import com.xwtec.infrastructure.export.task.export.IExportHandle;
import com.xwtec.infrastructure.export.utils.DateTimeUtils;
import com.xwtec.infrastructure.export.utils.RedisLock;
import com.xwtec.infrastructure.export.utils.SpringContextUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component("exportTask")
public class ExportTask {

    private static final Logger logger = LoggerFactory.getLogger(ExportTask.class);

    private static final Map<String, Supplier<IExportHandle>> EXPORT_HANDLERS = new HashMap<String, Supplier<IExportHandle>>() {
        {
            put(ExportFormat.CSV.toString(),
                    () -> SpringContextUtils.getBean(CsvExportHandle.class)
            );
        }

        {
            put(ExportFormat.EXCEL.toString(),
                    () -> SpringContextUtils.getBean(ExcelExportHandle.class));
        }
    };



    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    ExportService taskService;

    public void export(ExportProperties properties) {
        final PageInfo<ExportTaskInfo> tasks = taskService.getNonDoneTasks(
                properties.getPageSize(),
                ExportTaskStatus.INIT.val(),
                ExportTaskStatus.RUN.val());
        if (null == tasks || 0 == tasks.getSize()) {
            return;
        }

        for (final ExportTaskInfo task : tasks.getList()) {
            // skip
            if (task.getRetryNum() < 1) continue;

            final RedisLock redisLock = new RedisLock(redisTemplate,
                    "exportTaskLock:" + task.getTaskId(),
                    5 * 60,
                    500);

            try (IExportHandle exporter = EXPORT_HANDLERS.get(task.getExportFormat()).get()) {
                task.setTaskStatus(ExportTaskStatus.RUN.val());
                task.setLastTime(DateTimeUtils.getTodayChar17());
                try {
                    taskService.updateExportTask(task);
                } catch (final Exception e) {
                    logger.debug(e.getMessage(), e);
                }

                if (redisLock.lock()) {
                    // export
                    exporter.init(task, properties);
                    exporter.export(sqlSessionTemplate, "com.xwtec.infrastructure.export.ExportDao.export");
                    task.setTaskStatus(ExportTaskStatus.OK.val());
                    task.setTaskStatusMsg("导出成功");
                } else {
                    task.setTaskStatus(ExportTaskStatus.INIT.val());
                    task.setTaskStatusMsg("#cannot retrieve the redis lock");
                }
            } catch (final Exception e) {
                if (1 == task.getRetryNum() /* the last one */) {
                    task.setTaskStatus(ExportTaskStatus.ERROR.val());
                }
                final String errorMsg = e.getLocalizedMessage();
                task.setTaskStatusMsg(errorMsg.length() > 2000
                        ? errorMsg.substring(0, 2000)
                        : errorMsg
                );
                logger.error(e.getMessage(), e);
            } finally {
                // decrease one
                task.setRetryNum(task.getRetryNum() - 1);
                if (ExportTaskStatus.RUN.val() == task.getTaskStatus()) {
                    task.setTaskStatus(ExportTaskStatus.INIT.val());
                }
                task.setLastTime(DateTimeUtils.getTodayChar17());
                try {
                    taskService.updateExportTask(task);
                } catch (final Exception e) {
                    logger.debug(e.getMessage(), e);
                }
                redisLock.unlock();
            }
        }
    }
}
