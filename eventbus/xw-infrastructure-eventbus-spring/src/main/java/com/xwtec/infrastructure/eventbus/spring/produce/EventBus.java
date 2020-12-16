package com.xwtec.infrastructure.eventbus.spring.produce;

import com.xwtec.infrastructure.eventbus.spring.core.EventBusPayload;
import com.xwtec.infrastructure.eventbus.spring.core.EventMessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Component("eventBus")
public class EventBus implements IEventBus {

    private static final Logger log = LoggerFactory.getLogger(EventBus.class);

    @Autowired
    private Map<String, IEventBus> eventBusMap;


    public void post(EventBusPayload message) {
        EventMessageType eventMessageType = message.getEventMessageType();
        String eventbusType = eventMessageType.name().toLowerCase() + "EventBus";
        IEventBus eventBus = eventBusMap.get(eventbusType);
        if (eventBus != null) {
            eventBus.post(message);
        } else {
            throw new UnsupportedOperationException("not supported!!");
        }


//        switch (eventMessageType) {
//            case LOCAL:
//                eventBusMap.get("springEventBus").post(message);
//                break;
//            case NORMAL:
//                eventBusMap.get("normalEventBus").post(message);
//                break;
//            case ORDERLY:
//                eventBusMap.get("orderlyEventBus").post(message);
//                break;
//            case TRANSACTION:
//                eventBusMap.get("transactionEventBus").post(message);
//                break;
//            default:
//                throw new UnsupportedOperationException("not supported!!");
//        }

    }
}
