package com.xwtec.infrastructure.eventbus.spring.produce;


import com.xwtec.infrastructure.eventbus.spring.core.EventBusPayload;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 *<p>
 *
 *</p>
 *
 */
public interface IEventBusAsync {

    void post(EventBusPayload message, Consumer<EventBusResult> consumer);

}
