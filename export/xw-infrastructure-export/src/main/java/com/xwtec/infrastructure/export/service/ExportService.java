package com.xwtec.infrastructure.export.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xwtec.infrastructure.export.config.ExportProperties;
import com.xwtec.infrastructure.export.dao.ExportTaskMapper;
import com.xwtec.infrastructure.export.model.ExportFormat;
import com.xwtec.infrastructure.export.model.ExportTaskInfo;
import com.xwtec.infrastructure.export.model.ExportTaskStatus;
import com.xwtec.infrastructure.export.model.PrintFormat;
import com.xwtec.infrastructure.export.utils.DateTimeUtils;
import com.xwtec.infrastructure.export.utils.SqlHelper;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.List;

@Service
public class ExportService {

    private static final Logger logger = LoggerFactory.getLogger(ExportService.class);

    @Autowired
    SqlSession sqlSession;

    @Autowired
    ExportProperties exportProperties;

    @Autowired
    ExportTaskMapper exportTaskMapper;


    /**
     * 增加导出任务
     *  @param taskId               任务Id
     * @param taskName             任务名称
     * @param businessType         业务类型
     * @param exportFormat         导出格式
     * @param printFormat          是否导出头
     * @param tenantNum            租户号
     * @param fullMapperMethodName mybatis执行语句的名称
     * @param createMancode        创建人编码
     * @param createManname        创建人名称
     * @param args                 mybatis执行语句的参数
     */
    @Transactional
    public void addExportJob(String taskId,
                             String taskName,
                             String businessType,
                             ExportFormat exportFormat,
                             PrintFormat printFormat,
                             String tenantNum,
                             String fullMapperMethodName,
                             String createMancode,
                             String createManname,
                             Object... args) {
        if (StringUtils.isEmpty(taskId)) {
            throw new IllegalArgumentException("无效的taskId");
        }
        if (StringUtils.isEmpty(tenantNum)) {
            throw new IllegalArgumentException("无效的tenantNum");
        }

        final String sql = SqlHelper.getMapperSql(sqlSession, fullMapperMethodName, args);
        final List<String> selected = SqlHelper.getSelectItem(sql);

        final String selectedStr = selected.toString();
        logger.info("得到的sql:{}", sql);
        logger.info("得到的字段:{}", selectedStr);

        final ExportTaskInfo taskInfo = new ExportTaskInfo();
        taskInfo.setTaskId(taskId);
        taskInfo.setTaskName(taskName);
        taskInfo.setTaskStatus(ExportTaskStatus.INIT.val());
        taskInfo.setBusinessType(businessType);
        taskInfo.setRetryNum(Math.max(exportProperties.getRetryNum(), 1));
        taskInfo.setExportFormat(exportFormat.toString());
        taskInfo.setPrintFormat(printFormat.toString());

        taskInfo.setExportFileName(MessageFormat.format("{0}.{1}",
                fullMapperMethodName.contains(".")
                        ? fullMapperMethodName.substring(fullMapperMethodName.lastIndexOf(".") + 1)
                        : fullMapperMethodName,
                exportFormat.toString())
        );

        taskInfo.setSqlStatement(sql);
        taskInfo.setSqlSelected(selectedStr.substring(1, selectedStr.length() - 1));
        taskInfo.setTenantNum(tenantNum);
        taskInfo.setCreateMancode(createMancode);
        taskInfo.setCreateManname(createManname);

        final String lastTime = DateTimeUtils.getTodayChar17();
        taskInfo.setCreateTime(lastTime);
        taskInfo.setLastTime(lastTime);

        exportTaskMapper.insert(taskInfo);
    }

    public PageInfo<ExportTaskInfo> getExportTasks(ExportTaskInfo taskInfo, int pageSize) {
        return getExportTasks(taskInfo, 1, pageSize);
    }

    public PageInfo<ExportTaskInfo> getExportTasks(ExportTaskInfo taskInfo,
                                                   int pageIndex,
                                                   int pageSize) {
        final Page<ExportTaskInfo> paged = PageHelper.startPage(pageIndex, pageSize);
        exportTaskMapper.query(taskInfo);
        return paged.toPageInfo();
    }

    public PageInfo<ExportTaskInfo> getNonDoneTasks(int pageSize, int... states) {
        final Page<ExportTaskInfo> paged = PageHelper.startPage(1, pageSize);
        exportTaskMapper.queryTasks(1, states);
        return paged.toPageInfo();
    }

    @SuppressWarnings("UnusedReturnValue")
    public int updateExportTask(ExportTaskInfo taskInfo) {
        return exportTaskMapper.update(taskInfo);
    }

}
