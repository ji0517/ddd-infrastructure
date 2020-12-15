package com.xwtec.infrastructure.eventbus.spring.produce.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xwtec.infrastructure.eventbus.spring.core.EventBusPayload;
import com.xwtec.infrastructure.eventbus.spring.produce.IEventBus;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
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
    public void post(EventBusPayload message) {
//        log.info("normalEventBus");

        this.template.asyncSend(message.getTopic(), message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("异步消息发送成功，message = {}, SendStatus = {}", message, sendResult.getSendStatus());
            }

            @Override
            public void onException(Throwable e) {
                log.info("异步消息发送异常，exception = {}", e.getMessage());
            }
        });
//        template.syncSend(message.getTopic(),message);
    }
}
