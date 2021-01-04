package com.xwtec.infrastructure.eventbus.spring.produce.listener;

public interface ILocalTransaction {

    void executeLocalTransaction(String transactionId, Object payload);

    boolean checkLocalTransaction(String transactionId);

}
