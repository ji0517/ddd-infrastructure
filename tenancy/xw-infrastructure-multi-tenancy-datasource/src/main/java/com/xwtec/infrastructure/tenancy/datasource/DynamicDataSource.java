package com.xwtec.infrastructure.tenancy.datasource;

import com.xwtec.infrastructure.tenancy.datasource.utils.ConcurrentHashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.Set;

public class DynamicDataSource extends AbstractConcurrentRoutingDataSource {
    private final static Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);


    private Set<String> hasCreateDataSource = new ConcurrentHashSet<>();

    @Autowired
    private DataSourceFactory dataSourceFactory;

    @Override
    protected Object determineCurrentLookupKey() {
        String key = DataSourceKeyHolder.get();
        if (null == key) {
            return null;
        }
        if (hasCreateDataSource.contains(key)) {
            return key;
        }
        synchronized (this) {
            if (hasCreateDataSource.contains(key)) {
                return key;
            }
            DataSource dataSource = dataSourceFactory.createDataSource(key);
            if(dataSource == null){
                logger.info("没有找到数据源，数据源标识为{}",key);
            }
            addDataSource(key, dataSource);
            hasCreateDataSource.add(key);
            return key;
        }
    }
}
