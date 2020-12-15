package com.xwtec.infrastructure.eventbus.listener;

/**
 *<p>
 *
 *</p>
 */
public interface BusRegister {

    void register(IEventListener listener);

    void unregister(IEventListener listener);
}
