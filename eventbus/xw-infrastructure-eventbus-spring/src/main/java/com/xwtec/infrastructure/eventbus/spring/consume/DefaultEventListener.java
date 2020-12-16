package com.xwtec.infrastructure.eventbus.spring.consume;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.xwtec.infrastructure.eventbus.spring.core.EventBusPayload;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component("RocketMQListener_Default")
//@RocketMQMessageListener(consumerGroup = "test", topic = "test")
public class DefaultEventListener implements RocketMQListener<EventBusPayload> {

    private static final Logger log = LoggerFactory.getLogger(DefaultEventListener.class);

    private static ParserConfig config = new ParserConfig();

    static {
        config.setAutoTypeSupport(true);
    }

    @Autowired
    ApplicationEventPublisher publisher;

    @Override
    public void onMessage(EventBusPayload message) {
//        Object data = JSON.parseObject(message,EventBusPayload.class);
//        if (data != null) {
            publisher.publishEvent(message);
            log.info("NormalEventListener:{},{}",this.hashCode(),message.getPayload());

//        }  else {
//            log.error("MqEventSubscriber ignore not event type msg , context:[{}]", message);
//        }
    }
}
