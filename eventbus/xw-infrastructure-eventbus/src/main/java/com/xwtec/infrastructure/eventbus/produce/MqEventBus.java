package com.xwtec.infrastructure.eventbus.produce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.util.Objects;

import com.xwtec.infrastructure.eventbus.configure.EventBusProperties;
import com.xwtec.infrastructure.eventbus.core.IEvent;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *<p>
 *
 *</p>
 */
public class MqEventBus<E extends IEvent> implements IEventBus<E> {

    private static final Logger log = LoggerFactory.getLogger(MqEventBus.class);

    private static MqEventBus<? extends IEvent> INSTANCE;

    private final RocketMQTemplate rocketMQTemplate;

    private final EventBusProperties eventBusProperties;

    public MqEventBus(RocketMQTemplate rocketMQTemplate,
        EventBusProperties eventBusProperties) {
        Objects.requireNonNull(rocketMQTemplate, "rocketMQTemplate can't be null!");
        Objects.requireNonNull(eventBusProperties, "eventBusProperties can't be null!");

        this.rocketMQTemplate = rocketMQTemplate;
        this.eventBusProperties = eventBusProperties;
        MqEventBus.INSTANCE = this;
    }

    public static <E extends IEvent> void send(E event) {
        Objects.requireNonNull(INSTANCE, "MqEventBus instance hasn't be init");

        INSTANCE.post(event);
    }


    @Override
    public void post(IEvent event) {
        this.postObject(event);
    }

    private void postObject(Object event) {
        Objects.requireNonNull(event, "event can't be null");

        String content = JSON.toJSONString(event, SerializerFeature.WriteClassName);
        if (log.isDebugEnabled()) {
            log.debug("[{}] send event, content:[{}]", this.getClass().getName(), content);
        }

        SendCallback callback = new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("[{}] send event success, content:[{}], send result[{}]:",
                    this.getClass().getName(), content, sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("[{}] send event fail, content:[{}], error:",
                    this.getClass().getName(), content, throwable);
            }
        };
        if (event instanceof IEvent) {
            rocketMQTemplate.asyncSendOrderly(eventBusProperties.getTopic(),
                content, ((IEvent) event).getEventKey(), callback);
        }
    }
}
