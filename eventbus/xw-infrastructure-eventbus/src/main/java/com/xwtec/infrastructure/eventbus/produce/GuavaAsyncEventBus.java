package com.xwtec.infrastructure.eventbus.produce;

import com.alibaba.fastjson.JSON;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.xwtec.infrastructure.eventbus.configure.MqEventListenerContainerConfiguration;
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
public class GuavaAsyncEventBus<E extends IEvent>   implements BusRegister, IEventBus<E> {

    private static final Logger log = LoggerFactory.getLogger(GuavaAsyncEventBus.class);

    private static final Executor EXECUTOR = new ThreadPoolExecutor(
        Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors(),
        0L, TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(8192),
        new DiscardOldestPolicy() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                log.error("GuavaAsyncEventBus executor is full, discard oldest task, Runnable:{}", r);
            }
        });

    private final static EventBus EVENT_BUS = new AsyncEventBus("GuavaAsyncEventBus", EXECUTOR);
    private final static GuavaAsyncEventBus<? extends IEvent> INSTANCE = new GuavaAsyncEventBus();

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
