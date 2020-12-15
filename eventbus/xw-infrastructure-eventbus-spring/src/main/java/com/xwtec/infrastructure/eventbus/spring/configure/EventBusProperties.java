package com.xwtec.infrastructure.eventbus.spring.configure;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static org.apache.rocketmq.spring.annotation.RocketMQMessageListener.*;

/**
 * <p>
 *
 * </p>
 */
@ConfigurationProperties(prefix = "xw.eventbus")
public class EventBusProperties {

    AsyncProperties async = new AsyncProperties();

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


    public AsyncProperties getAsync() {
        return async;
    }

    public void setAsync(AsyncProperties async) {
        this.async = async;
    }
}
