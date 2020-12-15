package com.xwtec.infrastructure.eventbus.spring.produce.impl;

import com.xwtec.infrastructure.eventbus.spring.produce.IEventBus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("normalEventBus")
public class NormalEventBus implements IEventBus {

    private static final Logger log = LoggerFactory.getLogger(NormalEventBus.class);

    @Autowired
    private RocketMQTemplate template;

    @Override
    public void post(Object message) {
        log.info("normalEventBus");
        template.syncSend("test",message);
    }
}
