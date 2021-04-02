package com.xwtec.sale.infrastructure;

import com.xwtec.infrastructure.tenancy.datasource.DataSourceFactory;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.*;

@Component
public class DemoDataSourceFactory implements DataSourceFactory {
    private List<Map<String,String>> tsMap = new ArrayList<>();

    public DemoDataSourceFactory(){
        tsMap.add(new HashMap<>(){
            {
                put("1","jdbc:mysql://localhost:3306/db1");
            }
        });
        tsMap.add(new HashMap<>(){
            {
                put("2","jdbc:mysql://localhost:3306/db2");
            }
        });
    }




    @Override
    public DataSource createDefaultDataSource() {
//
//        HikariConfig config = new HikariConfig();
//        config.setPoolName(poolName);
//        config.setJdbcUrl(url); //数据源
//        config.setUsername(user); //用户名
//        config.setPassword(password); //密码
//        config.setDriverClassName(driverClassName);
//        config.setConnectionTimeout(connectionTimeout);
//        config.setIdleTimeout(idleTimeout);
//        config.setMaxLifetime(maxLifetime);
//        config.setMaximumPoolSize(maximumPoolSize);
//        config.setMinimumIdle(minimumIdle);
//        config.setAutoCommit(autoCommit);
//        config.setConnectionTestQuery(connectionTestQuery);
//        config.addDataSourceProperty("cachePrepStmts", cachePrepStmts); //是否自定义配置，为true时下面两个参数才生效
//        config.addDataSourceProperty("prepStmtCacheSize", prepStmtCacheSize); //连接池大小默认25，官方推荐250-500
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", prepStmtCacheSqlLimit); //单条语句最大长度默认256，官方推荐2048
//
//        HikariDataSource ds = new HikariDataSource(config);
////        hikariDataSource.
//        return ds;


        HikariDataSource dataSource = (HikariDataSource)DataSourceBuilder.create().username("root").password("jijl2001")
                .url("jdbc:mysql://localhost:3306/db_common")
                .build();
        return dataSource;
//        return null;
    }

    @Override
    public DataSource createDataSource(String key) {
        Optional<Map<String,String>> mapOptional = tsMap.stream().filter(e->e.containsKey(key)).findAny();
        if(mapOptional.isPresent()){
           String v = mapOptional.get().get(key);
            HikariDataSource dataSource = (HikariDataSource)DataSourceBuilder.create().username("root").password("jijl2001")
                    .url(v)
                    .build();
            return dataSource;
        }else {
            return null;
        }
    }
}
