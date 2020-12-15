package com.xwtec.infrastructure.eventbus.produce;

import com.alibaba.fastjson.JSON;
import com.google.common.eventbus.EventBus;
import java.util.Objects;

import com.xwtec.infrastructure.eventbus.core.IEvent;
import com.xwtec.infrastructure.eventbus.listener.BusRegister;
import com.xwtec.infrastructure.eventbus.listener.IEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *<p>
 *
 *</p>
 *
 */
public class GuavaEventBus<E extends IEvent> implements BusRegister, IEventBus<E> {

    private static final Logger log = LoggerFactory.getLogger(GuavaEventBus.class);

    private final static EventBus EVENT_BUS = new EventBus();
    private final static GuavaEventBus<? extends IEvent> INSTANCE = new GuavaEventBus();

    public static <E extends IEvent> void send(E event) {
        INSTANCE.post(event);
    }

    public static void registerListener(IEventListener listener) {
        Objects.requireNonNull(INSTANCE, "MqEventBus instance hasn't be init");

        INSTANCE.register(listener);
    }

    public static void unregisterListener(IEventListener listener) {
        Objects.requireNonNull(INSTANCE, "MqEventBus instance hasn't be init");

        INSTANCE.unregister(listener);
    }

    @Override
    public void register(IEventListener listener) {
        EVENT_BUS.register(listener);
    }

    @Override
    public void unregister(IEventListener listener) {
        EVENT_BUS.unregister(listener);
    }

    @Override
    public void post(IEvent event) {
        Objects.requireNonNull(event, "event can't be null");

        if (log.isDebugEnabled()) {
            log.debug("[{}] send event, content:[{}]", this.getClass().getName(), JSON.toJSONString(event));
        }
        EVENT_BUS.post(event);
    }
}
