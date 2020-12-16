package com.xwtec.infrastructure.eventbus.spring.produce.listener;

public interface ILocalTransaction {

    void run(String transactionId,Object arg);

    boolean complete(String transactionId);

}
