package com.xwtec.infrastructure.eventbus.spring.produce.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("test1LocalTransaction")
public class LocalTransaction implements ILocalTransaction {

    private static final Logger log = LoggerFactory.getLogger(CustomRocketMQTransactionListener.class);


    @Override
    public void run(String transactionId, Object arg) {
        log.info("本地事务执行，{},{}", transactionId, arg);
    }

    @Override
    public boolean complete(String transactionId) {
        log.info("本地事务执行是否完成，{}", true);
        return true;
    }
}
