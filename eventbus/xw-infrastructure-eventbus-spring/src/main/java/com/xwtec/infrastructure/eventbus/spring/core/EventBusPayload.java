package com.xwtec.infrastructure.eventbus.spring.core;

public class EventBusPayload {

    private EventMessageType eventMessageType;
    private String topic;
    private Object payload;

    private EventBusPayload(){

    }

    private EventBusPayload(EventMessageType eventMessageType,String topic,Object payload){
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

    public EventMessageType getEventMessageType() {
        return eventMessageType;
    }

    public String getTopic() {
        return topic;
    }

    public Object getPayload() {
        return payload;
    }

}
