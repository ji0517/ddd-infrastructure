package com.xwtec.infrastructure.eventbus.spring.configure;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@EnableScheduling
public class ExceutorConfiguration extends AsyncConfigurerSupport {

    @Resource
    private EventBusProperties eventBusProperties;

    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(eventBusProperties.getAsync().getCorePoolSize());
        threadPool.setMaxPoolSize(eventBusProperties.getAsync().getMaxPoolSize());
        threadPool.setQueueCapacity(eventBusProperties.getAsync().getQueueCapacity());
        threadPool.setKeepAliveSeconds(eventBusProperties.getAsync().getKeepAliveSeconds());
        threadPool.setThreadNamePrefix("eventbus-async-executor-");
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPool;
    }

    @Override
    public Executor getAsyncExecutor() {
        return asyncExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

}
