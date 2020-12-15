package com.xwtec.infrastructure.eventbus.spring.consume;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "test", topic = "test")
public class NormalEventListener implements RocketMQListener<Object> {

    private static final Logger log = LoggerFactory.getLogger(NormalEventListener.class);

    @Override
    public void onMessage(Object message) {
        log.info("NormalEventListener:{}",message);
    }
}
