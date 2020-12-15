package com.xwtec.infrastructure.eventbus.consume;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.xwtec.infrastructure.eventbus.core.IEvent;
import com.xwtec.infrastructure.eventbus.produce.GuavaEventBus;
import com.xwtec.infrastructure.eventbus.produce.MqEventBus;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *<p>
 *
 *</p>
 *
 */
public class MqEventSubscriber<E extends IEvent> implements  RocketMQListener<String> {


    private static final Logger log = LoggerFactory.getLogger(MqEventSubscriber.class);

    private static ParserConfig config = new ParserConfig();

    static {
        config.setAutoTypeSupport(true);
    }

    @Override
    public void onMessage(String msg) {
        if (msg.contains("com.xwtec.infrastructure.eventbus.core.DelayEvent")) {
            if(log.isDebugEnabled()){
                log.debug("MqEventSubscriber receive delay event:[{}]", msg);
            }
        }else {
            log.info("MqEventSubscriber receive msg:[{}]", msg);
        }
        if (StringUtils.isBlank(msg)) {
            log.warn("MqEventSubscriber receive msg, but msg is blank");
            return;
        }

        try {
            Object data = JSON.parse(msg, config);
            if (data instanceof IEvent) {
                GuavaEventBus.send((IEvent) data);
            }  else {
                log.error("MqEventSubscriber ignore not event type msg , context:[{}]", msg);
            }
        } catch (Exception e) {
            log.error("Msg:[] parse to event or send event fail, error:{}", msg, e);
        }
    }
}
