package com.xwtec.infrastructure.eventbus.spring.core;

import java.util.UUID;

public class EventBusPayload {

    private String id;
    private EventMessageType eventMessageType;
    private String topic;
    private String group;
    private Object payload;

    private EventBusPayload(){

    }

    private EventBusPayload(EventMessageType eventMessageType,String group,String topic,Object payload){
        this.id = UUID.randomUUID().toString();
        this.eventMessageType = eventMessageType;
        this.payload = payload;
        this.topic = topic;
        this.group = group;
    }


    public static   EventBusPayload local(Object payload){
        return new EventBusPayload(EventMessageType.LOCAL,"none","none",payload);
    }

    public static EventBusPayload normal(String group,String topic,  Object payload){
        return new EventBusPayload(EventMessageType.NORMAL,group,topic,payload);
    }

    public static EventBusPayload orderly(String group,String topic,  Object payload){
        return new EventBusPayload(EventMessageType.ORDERLY,group,topic,payload);
    }

    public static  EventBusPayload transaction(String group,String topic,  Object payload){
        return new EventBusPayload(EventMessageType.TRANSACTION,group,topic,payload);
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
