package com.xwtec.infrastructure.eventbus.spring.configure;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.apache.rocketmq.spring.annotation.RocketMQMessageListener.*;

/**
 * <p>
 *
 * </p>
 */
@ConfigurationProperties(prefix = "xw.eventbus")
public class EventBusProperties {

    AsyncProperties async = new AsyncProperties();

    private List<ConsumerProperties> consumers;

    //异步对列配置
    public class AsyncProperties {
        //异步核心线程数，默认：2
        private int corePoolSize = 2;

        //线程存活时间，默认：300
        private int keepAliveSeconds = 300;

        //异步最大线程数，默认：50
        private int maxPoolSize = 50;

        //队列容量，默认：10000
        private int queueCapacity = 10000;

        public int getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public int getKeepAliveSeconds() {
            return keepAliveSeconds;
        }

        public void setKeepAliveSeconds(int keepAliveSeconds) {
            this.keepAliveSeconds = keepAliveSeconds;
        }

        public int getMaxPoolSize() {
            return maxPoolSize;
        }

        public void setMaxPoolSize(int maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
        }

        public int getQueueCapacity() {
            return queueCapacity;
        }

        public void setQueueCapacity(int queueCapacity) {
            this.queueCapacity = queueCapacity;
        }
    }

    //消费者
    public static class ConsumerProperties{

        private String consumerGroup;
        private String topic;
        private String type;


        public String getConsumerGroup() {
            return consumerGroup;
        }

        public void setConsumerGroup(String consumerGroup) {
            this.consumerGroup = consumerGroup;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }


    public AsyncProperties getAsync() {
        return async;
    }

    public void setAsync(AsyncProperties async) {
        this.async = async;
    }

    public List<ConsumerProperties> getConsumers() {
        return consumers;
    }

    public void setConsumers(List<ConsumerProperties> consumers) {
        this.consumers = consumers;
    }
}
