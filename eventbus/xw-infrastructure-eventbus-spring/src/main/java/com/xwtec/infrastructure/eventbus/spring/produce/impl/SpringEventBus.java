package com.xwtec.infrastructure.eventbus.spring.produce.impl;

import com.xwtec.infrastructure.eventbus.spring.produce.IEventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component("springEventBus")
public class SpringEventBus implements IEventBus {

    private static final Logger log = LoggerFactory.getLogger(SpringEventBus.class);

    @Autowired
    ApplicationEventPublisher publisher;

    @Override
    public void post(Object message) {
        log.info("SpringEventBus");
        if(message!=null){
            publisher.publishEvent(message);
        }
    }
}