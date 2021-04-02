package com.xwtec.infrastructure.tenancy.web.config;

import com.xwtec.infrastructure.tenancy.web.interceptor.TenantInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private  TenantProperties properties;

    @Autowired
    TenantInterceptor tenantInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (properties.isEnable()) {
            InterceptorRegistration interceptorRegistration = registry.addInterceptor(tenantInterceptor);
            if(properties.getAddPathPatterns()!=null&&properties.getAddPathPatterns().length>0){
                interceptorRegistration.addPathPatterns(properties.getAddPathPatterns());
            }
            if(properties.getExcludePathPatterns()!=null&&properties.getExcludePathPatterns().length>0){
                interceptorRegistration.excludePathPatterns(properties.getExcludePathPatterns());
            }

        }
    }

}
