package com.xwtec.infrastructure.export.task.export;

import com.xwtec.infrastructure.export.config.ExportProperties;
import com.xwtec.infrastructure.export.model.ExportTaskInfo;
import org.mybatis.spring.SqlSessionTemplate;

/**
 * 导出处理接口
 */
public interface IExportHandle extends AutoCloseable {

    public void init(final ExportTaskInfo taskInfo, final ExportProperties properties);

    /**
     * 写处理
     * @param sqlSessionTemplate
     * @param sqlExportTemplate
     */
    public void export(SqlSessionTemplate sqlSessionTemplate, String sqlExportTemplate);
}
