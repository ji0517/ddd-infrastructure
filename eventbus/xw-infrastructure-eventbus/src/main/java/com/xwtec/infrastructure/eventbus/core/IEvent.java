package com.xwtec.infrastructure.eventbus.core;

import java.io.Serializable;
import java.util.UUID;

/**
 *<p>
 * 抽象事件接口
 *</p>
 *
 */
public interface IEvent extends Serializable {

    static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    String getEventKey();
}
