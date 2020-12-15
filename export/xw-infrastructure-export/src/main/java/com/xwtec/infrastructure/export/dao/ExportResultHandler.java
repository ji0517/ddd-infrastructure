package com.xwtec.infrastructure.export.dao;

import com.xwtec.infrastructure.export.config.ExportProperties;
import com.xwtec.infrastructure.export.model.ExportTaskInfo;
import com.xwtec.infrastructure.export.task.ExportException;
import com.xwtec.infrastructure.export.task.export.IExportHandle;
import com.xwtec.infrastructure.export.utils.SpringContextUtils;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.util.*;

public class ExportResultHandler /*implements ResultHandler<LinkedHashMap>*/ {

    private static final String LINE_SEPARATOR = "\n";
    // 这是每批处理的大小
    private final static int BATCH_SIZE = 1000;
    private int size;
    // 存储每批数据的临时容器
    private List<LinkedHashMap> datas = new ArrayList<>();

    private ExportTaskInfo exportTaskInfo;
    private ExportProperties properties;
    private IExportHandle exportHandle;


    public ExportResultHandler(ExportProperties properties, ExportTaskInfo exportTaskInfo) {
        this.properties = properties;
        this.exportTaskInfo = exportTaskInfo;

        String beanName = exportTaskInfo.getExportFormat().toLowerCase() + "ExportHandle";
        Object target = SpringContextUtils.getBean(beanName);
        if (target != null) {
            exportHandle = (IExportHandle) target;
        }else {
            throw  new ExportException("未找到"+exportTaskInfo.getExportFormat() +"的实现类！" );
        }
    }


    //@Override
    public void handleResult(ResultContext<? extends LinkedHashMap> resultContext) {
        if (exportHandle != null) {
            // 获取流式查询每次返回的单条结果
            LinkedHashMap data = resultContext.getResultObject();
            // 分批
            datas.add(data);
            size++;
            if (size == BATCH_SIZE) {
                handle();
            }
        }
    }

    private void handle() {
        try {
            // exportHandle.export(exportTaskInfo, "");
        } catch (ExportException e) {
            throw e;
        } finally {
            size = 0;
            datas.clear();
        }
    }

    // 这个方法给外面调用，用来完成最后一批数据处理
    public void end() {
        handle();// 处理最后一批不到BATCH_SIZE的数据
    }
}
