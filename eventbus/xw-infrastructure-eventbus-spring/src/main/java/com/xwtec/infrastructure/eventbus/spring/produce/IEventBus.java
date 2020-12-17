package com.xwtec.infrastructure.eventbus.spring.produce;


import com.xwtec.infrastructure.eventbus.spring.core.EventBusPayload;

/**
 *<p>
 *
 *</p>
 *
 */
public interface IEventBus {

    EventBusResult post(EventBusPayload message);
}
