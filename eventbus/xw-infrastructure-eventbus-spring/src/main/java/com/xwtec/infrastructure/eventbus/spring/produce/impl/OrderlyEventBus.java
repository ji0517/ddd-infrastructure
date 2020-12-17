package com.xwtec.infrastructure.eventbus.spring.produce.impl;

import com.xwtec.infrastructure.eventbus.spring.core.EventBusPayload;
import com.xwtec.infrastructure.eventbus.spring.produce.EventBusResult;
import com.xwtec.infrastructure.eventbus.spring.produce.IEventBus;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
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
    public EventBusResult post(EventBusPayload message) {
        log.debug("OrderlyEventBus");
        if (message != null) {
            try {
                SendResult sendResult = this.template.syncSendOrderly(message.getTopic(), message, message.getTopic() + UUID.randomUUID().toString());
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
