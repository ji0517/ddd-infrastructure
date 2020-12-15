package com.xwtec.infrastructure.eventbus.produce;


import com.xwtec.infrastructure.eventbus.core.IEvent;

/**
 *<p>
 *
 *</p>
 *
 */
public interface IEventBus<E extends IEvent> {

    void post(E event);
}
