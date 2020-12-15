package com.xwtec.infrastructure.export.config;

import com.xwtec.infrastructure.export.task.SchedulingRunnable;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.annotation.PostConstruct;

/**
 * @description: 定时任务配置类
 **/
//@Configuration
@EnableConfigurationProperties(ExportProperties.class)
@MapperScan({ "com.xwtec.infrastructure.export.dao" })
public class SchedulingConfig {

    @Autowired
    ExportProperties exportProperties;

//    @Autowired
//    CronTaskRegistrar cronTaskRegistrar;

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        // 定时任务执行线程池核心线程数
        taskScheduler.setPoolSize(exportProperties.getPoolSize());
        taskScheduler.setRemoveOnCancelPolicy(true);
        taskScheduler.setThreadNamePrefix("TaskSchedulerThreadPool-");
        return taskScheduler;
    }

    @Bean(name = "cronTaskRegistrar")
    public CronTaskRegistrar cronTaskRegistrar() {
        CronTaskRegistrar cronTaskRegistrar = new CronTaskRegistrar();
        return cronTaskRegistrar;
    }

    @Bean
    public  SchedulingRunnable schedulingRunnable(CronTaskRegistrar cronTaskRegistrar){
        SchedulingRunnable task = new SchedulingRunnable("exportTask", "export", exportProperties);
        cronTaskRegistrar.addCronTask(task, exportProperties.getCron());
        return task;
    }

}
