package com.xwtec.infrastructure.eventbus.spring.produce;

import com.xwtec.infrastructure.eventbus.spring.core.EventBusPayload;
import com.xwtec.infrastructure.eventbus.spring.core.EventMessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Component("eventBusAsync")
public class EventBusAsync implements IEventBusAsync {

    private static final Logger log = LoggerFactory.getLogger(EventBusAsync.class);

    @Autowired
    private Map<String, IEventBusAsync> eventBusMap;


    public void post(EventBusPayload message, Consumer<EventBusResult> consumer) {
        EventMessageType eventMessageType = message.getEventMessageType();
        String eventbusType = eventMessageType.name().toLowerCase() + "EventBusAsync";
        IEventBusAsync eventBus = eventBusMap.get(eventbusType);
        if (eventBus != null) {
            eventBus.post(message, (result) -> {
                consumer.accept(result);
            });
        } else {
            throw new UnsupportedOperationException("not supported!!");
        }
    }
}
