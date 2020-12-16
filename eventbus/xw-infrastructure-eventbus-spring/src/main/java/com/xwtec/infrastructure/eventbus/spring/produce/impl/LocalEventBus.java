package com.xwtec.infrastructure.eventbus.spring.produce.impl;

import com.xwtec.infrastructure.eventbus.spring.core.EventBusPayload;
import com.xwtec.infrastructure.eventbus.spring.produce.IEventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component("localEventBus")
public class LocalEventBus implements IEventBus {

    private static final Logger log = LoggerFactory.getLogger(LocalEventBus.class);

    @Autowired
    ApplicationEventPublisher publisher;

    @Override
    public void post(EventBusPayload message) {
        log.info("SpringEventBus");
        if(message!=null){
            publisher.publishEvent(message.getPayload());
        }
    }
}
