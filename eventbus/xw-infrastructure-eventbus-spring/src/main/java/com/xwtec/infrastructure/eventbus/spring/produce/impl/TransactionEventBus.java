package com.xwtec.infrastructure.eventbus.spring.produce.impl;

import com.xwtec.infrastructure.eventbus.spring.core.EventBusPayload;
import com.xwtec.infrastructure.eventbus.spring.produce.EventBusResult;
import com.xwtec.infrastructure.eventbus.spring.produce.IEventBus;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("transactionEventBus")
public class TransactionEventBus implements IEventBus {

    private static final Logger log = LoggerFactory.getLogger(TransactionEventBus.class);

    @Autowired
    private RocketMQTemplate template;

    @Override
    public EventBusResult post(EventBusPayload message) {
        log.debug("TransactionEventBus");
        if (message != null) {
            try {
                String transactionId = UUID.randomUUID().toString();
                long beginTimestampFirst = System.currentTimeMillis();
                TransactionSendResult result = this.template.sendMessageInTransaction(
                        message.getTopic(),
                        MessageBuilder.withPayload(message)
                                .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId)
                                .setHeader(RocketMQHeaders.TOPIC, message.getTopic())
                                .build(),
                        message);
                long endTimestampFirst = System.currentTimeMillis();
                log.info("发送事务消息（半消息）完成：time = {},result = {}", endTimestampFirst - beginTimestampFirst, result);
                return EventBusResult.ok("发送事务消息完成");
            } catch (Exception ex) {
                log.error("消息发送异常:", ex);
                return EventBusResult.fail("消息发送异常：" + ex.getMessage());

            }
        } else {
            return EventBusResult.fail("参数EventBusPayload message值为null");
        }
    }
}
