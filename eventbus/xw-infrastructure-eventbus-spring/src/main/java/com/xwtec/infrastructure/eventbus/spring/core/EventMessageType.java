package com.xwtec.infrastructure.eventbus.spring.core;

public enum EventMessageType {
    //本地消息
    LOCAL,

    //一般消息
    NORMAL,

    //顺序消息
    ORDERLY,

    //事务消息
    TRANSACTION
}