package com.xwtec.infrastructure.eventbus.spring.produce.listener;

import com.xwtec.infrastructure.eventbus.spring.consume.DefaultEventListener;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;


@RocketMQTransactionListener
public class CustomRocketMQTransactionListener implements ApplicationContextAware, RocketMQLocalTransactionListener {

    private static final Logger log = LoggerFactory.getLogger(CustomRocketMQTransactionListener.class);

    private ConfigurableApplicationContext applicationContext;


    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object arg) {
        MessageHeaders headers = message.getHeaders();
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        String topic = (String) headers.get(RocketMQHeaders.TOPIC);
        ILocalTransaction localTransaction = applicationContext.getBean(topic + "LocalTransaction", ILocalTransaction.class);
        try {
            //执行本地事务
            localTransaction.run(transactionId, arg);
            //返回本地事务执行状态为提交，发送事务消息
            log.info("本地事务正常，消息可以被发送了..");
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            log.error("本地事务异常", e);
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {

        try {
            MessageHeaders headers = message.getHeaders();
            String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
            String topic = (String) headers.get(RocketMQHeaders.TOPIC);
            ILocalTransaction localTransaction = applicationContext.getBean(topic + "LocalTransaction", ILocalTransaction.class);

            if (localTransaction.complete(transactionId)) {
                return RocketMQLocalTransactionState.COMMIT;
            }
            return RocketMQLocalTransactionState.ROLLBACK;
        } catch (Exception ex) {
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
    }
}
