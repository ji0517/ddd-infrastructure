package com.xwtec.infrastructure.eventbus.spring.produce.impl;

import com.xwtec.infrastructure.eventbus.spring.core.EventBusPayload;
import com.xwtec.infrastructure.eventbus.spring.produce.EventBusResult;
import com.xwtec.infrastructure.eventbus.spring.produce.IEventBus;
import com.xwtec.infrastructure.eventbus.spring.produce.IEventBusAsync;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component("normalEventBusAsync")
public class NormalEventBusAsync implements IEventBusAsync {

    private static final Logger log = LoggerFactory.getLogger(NormalEventBusAsync.class);

    @Autowired
    private RocketMQTemplate template;

    @Override
    public void post(EventBusPayload message, Consumer<EventBusResult> consumer) {
        log.debug("NormalEventBusAsync");
        if (message != null) {
            try {
                this.template.asyncSend(message.getTopic(), message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        log.debug("异步消息发送成功，message = {}, SendStatus = {}", message, sendResult.getSendStatus());
                        if (sendResult.getSendStatus().name().equals(SendStatus.SEND_OK.name())) {
                            consumer.accept(EventBusResult.ok());
                        } else {
                            consumer.accept(EventBusResult.fail(sendResult.getSendStatus().toString()));
                        }
                    }

                    @Override
                    public void onException(Throwable e) {
                        consumer.accept(EventBusResult.fail("消息发送异常：" + e.getMessage()));
                        log.info("异步消息发送异常，exception = {}", e.getMessage());

                    }
                });
            } catch (Exception ex) {
                consumer.accept(EventBusResult.fail("消息发送异常：" + ex.getMessage()));
                log.error("消息发送异常:", ex);
            }
        } else {
            consumer.accept(EventBusResult.fail("参数EventBusPayload message值为null"));
        }
    }
}
