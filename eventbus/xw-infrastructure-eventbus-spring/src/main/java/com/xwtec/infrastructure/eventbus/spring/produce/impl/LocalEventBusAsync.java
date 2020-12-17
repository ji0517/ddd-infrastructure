package com.xwtec.infrastructure.eventbus.spring.produce.impl;

import com.xwtec.infrastructure.eventbus.spring.core.EventBusPayload;
import com.xwtec.infrastructure.eventbus.spring.produce.EventBusResult;
import com.xwtec.infrastructure.eventbus.spring.produce.IEventBus;
import com.xwtec.infrastructure.eventbus.spring.produce.IEventBusAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Component("localEventBusAsync")
public class LocalEventBusAsync implements IEventBusAsync {

    private static final Logger log = LoggerFactory.getLogger(LocalEventBusAsync.class);

    @Autowired
    ApplicationEventPublisher publisher;

    @Override
    public void post(EventBusPayload message, Consumer<EventBusResult> consumer) {
        log.debug("SpringEventBusAsync");
        if (message != null) {
            try {
                publisher.publishEvent(message.getPayload());
                consumer.accept(EventBusResult.ok());
            } catch (Exception ex) {
                consumer.accept(EventBusResult.fail("消息发送异常：" + ex.getMessage()));
                log.error("消息发送异常:",ex);
            }
        } else {
            consumer.accept(EventBusResult.fail("参数EventBusPayload message值为null"));
        }
    }
}
