package com.xwtec.sample.eventbus.controller;

import com.xwtec.infrastructure.eventbus.spring.configure.EventBusAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class AccountListener {

    private static final Logger log = LoggerFactory.getLogger(AccountListener.class);

    /**
     * 1. 发送邮件、短信
     */
    @EventListener
    public void processAccountCreatedEvent1(AccountCreatedEvent event) {
        log.info("发送邮件、短信:{}",event.toString());
    }

    /**
     * 2. 添加积分等，@Order(100) 用来设定执行顺序
     */
    @EventListener
    @Order(100)
    public void processAccountCreatedEvent2(AccountCreatedEvent event) {
        log.info("添加积分:{}",event.toString());
    }

}
