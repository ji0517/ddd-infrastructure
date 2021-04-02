package com.xwtec.infrastructure.tenancy.datasource;

import com.xwtec.infrastructure.tenancy.web.context.TenantContextHolder;

class DataSourceKeyHolder {
//    private final static ThreadLocal<String> dataSourceHolder = new ThreadLocal<>();

    static void set(String key) {
        throw new UnsupportedOperationException("不实现DataSourceKeyHolder.set方法");
//        dataSourceHolder.set(key);
    }

    static String get() {
        return TenantContextHolder.getTenant();
//        return dataSourceHolder.get();
    }

    static void clear() {
        throw new UnsupportedOperationException("不实现DataSourceKeyHolder.clear方法");
//        dataSourceHolder.remove();
    }
}
