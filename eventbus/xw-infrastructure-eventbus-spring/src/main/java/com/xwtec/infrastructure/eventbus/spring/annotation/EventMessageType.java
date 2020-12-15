package com.xwtec.infrastructure.eventbus.spring.annotation;

import org.apache.rocketmq.common.filter.ExpressionType;

public enum EventMessageType {
    //本地消息
    LOCAL,

    //一般消息
    NORMAL,

    //顺序消息
    ORDEARLY,

    //事务消息
    TRANSACTION
}