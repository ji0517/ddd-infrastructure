package com.xwtec.infrastructure.eventbus.spring.core;

import java.util.UUID;

public class EventBusPayload {

    private String id;
    private EventMessageType eventMessageType;
    private String topic;
    private Object payload;

    public EventBusPayload(){

    }

    private EventBusPayload(EventMessageType eventMessageType,String topic,Object payload){
        this.id = UUID.randomUUID().toString();
        this.eventMessageType = eventMessageType;
        this.payload = payload;
        this.topic = topic;
    }


    public static   EventBusPayload local(Object payload){
        return new EventBusPayload(EventMessageType.LOCAL,"none",payload);
    }

    public static EventBusPayload normal(String topic,  Object payload){
        return new EventBusPayload(EventMessageType.NORMAL,topic,payload);
    }

    public static EventBusPayload orderly(String topic,  Object payload){
        return new EventBusPayload(EventMessageType.ORDERLY,topic,payload);
    }

    public static  EventBusPayload transaction(String topic,  Object payload){
        return new EventBusPayload(EventMessageType.TRANSACTION,topic,payload);
    }

    public String getId() {
        return id;
    }

    public EventMessageType getEventMessageType() {
        return eventMessageType;
    }

    public String getTopic() {
        return topic;
    }

    public Object getPayload() {
        return payload;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEventMessageType(EventMessageType eventMessageType) {
        this.eventMessageType = eventMessageType;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
