package com.xwtec.infrastructure.tenancy.datasource;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class DataSourceComponentRegister implements ImportBeanDefinitionRegistrar {

    private final static String DEFAULT_BASE_PACKAGE = "com.xwtec.infrastructure.tenancy.datasource";
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        ClassPathBeanDefinitionScanner beanDefinitionScanner = new ClassPathBeanDefinitionScanner(registry);
        beanDefinitionScanner.scan(DEFAULT_BASE_PACKAGE);
    }
}
