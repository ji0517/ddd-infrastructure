package com.xwtec.infrastructure.eventbus.listener;

import com.xwtec.infrastructure.eventbus.produce.GuavaAsyncEventBus;
import com.xwtec.infrastructure.eventbus.produce.GuavaEventBus;

/**
 *<p>
 *
 *</p>
 *
 */
public class AbstractEventListener implements IEventListener {

    @Override
    public void afterPropertiesSet() throws Exception {
        GuavaEventBus.registerListener(this);
        GuavaAsyncEventBus.registerListener(this);
    }

    @Override
    public void destroy() throws Exception {
        GuavaEventBus.unregisterListener(this);
        GuavaAsyncEventBus.unregisterListener(this);
    }
}
