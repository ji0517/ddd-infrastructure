package com.xwtec.infrastructure.eventbus.spring.produce.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xwtec.infrastructure.eventbus.spring.core.EventBusPayload;
import com.xwtec.infrastructure.eventbus.spring.produce.EventBusResult;
import com.xwtec.infrastructure.eventbus.spring.produce.IEventBus;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
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
    public EventBusResult post(EventBusPayload message) {
        log.debug("NormalEventBus");
        if (message != null) {
            try {
                SendResult sendResult = this.template.syncSend(message.getTopic(), message);
                log.debug("同步消息发送成功，message = {}, SendStatus = {}", message, sendResult.getSendStatus());
                if (sendResult.getSendStatus().name().equals(SendStatus.SEND_OK.name())) {
                    return EventBusResult.ok();
                } else {
                    return EventBusResult.fail(sendResult.getSendStatus().toString());
                }
            } catch (Exception ex) {
                log.error("消息发送异常:", ex);
                return EventBusResult.fail("消息发送异常：" + ex.getMessage());
            }
        } else {
            return EventBusResult.fail("参数EventBusPayload message值为null");
        }
    }
}
