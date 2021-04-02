package com.xwtec.infrastructure.tenancy.web.config;

import com.xwtec.infrastructure.tenancy.web.interceptor.TenantInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(value = TenantProperties.class)
@Import(WebConfigurer.class)
public class TenantAutoConfig {

    @Autowired
    private  TenantProperties properties;

    @Bean
    public TenantInterceptor tenantInterceptor() {
        return new TenantInterceptor(properties);
    }

}
