package com.xwtec.infrastructure.export.task.export;

import com.xwtec.infrastructure.export.config.ExportProperties;
import com.xwtec.infrastructure.export.model.ExportTaskInfo;
import com.xwtec.infrastructure.export.task.ExportException;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

@Component("excelExportHandle")
public class ExcelExportHandle implements IExportHandle {

    @Override
    public void init(ExportTaskInfo taskInfo, ExportProperties properties) {

    }

    @Override
    public void export(SqlSessionTemplate sqlSessionTemplate, String sqlExportTemplate) {
        throw new UnsupportedOperationException("尚未实现");
    }

    @Override
    public void close() throws ExportException {

    }
}
