package com.xwtec.sample.eventbus.controller;

public class AccountCreatedEvent {

    public AccountCreatedEvent(String type){
        this.type = type;
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AccountCreatedEvent{" +
                "type='" + type + '\'' +
                '}';
    }
}
