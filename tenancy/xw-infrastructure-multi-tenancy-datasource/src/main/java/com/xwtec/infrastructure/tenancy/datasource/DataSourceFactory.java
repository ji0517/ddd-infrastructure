package com.xwtec.infrastructure.tenancy.datasource;

import javax.sql.DataSource;

public interface DataSourceFactory {

    /**
     * 创建默认数据源
     */
    DataSource createDefaultDataSource();

    /**
     * 根据所给key返回datasource
     * <p>
     * 此处key和数据源一一对应
     */
    DataSource createDataSource(String key);

}
