package com.xwtec.infrastructure.eventbus.spring.produce.impl;

import com.xwtec.infrastructure.eventbus.spring.core.EventBusPayload;
import com.xwtec.infrastructure.eventbus.spring.produce.EventBusResult;
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
    public EventBusResult post(EventBusPayload message) {
        log.info("SpringEventBus");
        if(message!=null){
            try {
                publisher.publishEvent(message.getPayload());
                return EventBusResult.ok();
            } catch (Exception ex) {
                log.error("消息发送异常:",ex);
                return EventBusResult.fail("消息发送异常：" + ex.getMessage());
            }
        }
        return EventBusResult.fail("参数EventBusPayload message值为null");

    }
}
