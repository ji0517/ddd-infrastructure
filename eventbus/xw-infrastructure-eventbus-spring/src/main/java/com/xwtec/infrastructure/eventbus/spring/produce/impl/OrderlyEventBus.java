package com.xwtec.infrastructure.eventbus.spring.produce.impl;

import com.xwtec.infrastructure.eventbus.spring.core.EventBusPayload;
import com.xwtec.infrastructure.eventbus.spring.produce.IEventBus;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("orderlyEventBus")
public class OrderlyEventBus implements IEventBus {

    private static final Logger log = LoggerFactory.getLogger(OrderlyEventBus.class);

    @Autowired
    private RocketMQTemplate template;

    @Override
    public void post(EventBusPayload message) {

        this.template.asyncSendOrderly(
                message.getTopic(),
                message,
                message.getTopic() + UUID.randomUUID().toString(),
                new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("异步顺序消息发送成功，message = {}, SendStatus = {}", message, sendResult.getSendStatus());
            }

            @Override
            public void onException(Throwable e) {
                log.info("异步顺序消息发送异常，exception = {}", e.getMessage());
            }
        });
    }
}
