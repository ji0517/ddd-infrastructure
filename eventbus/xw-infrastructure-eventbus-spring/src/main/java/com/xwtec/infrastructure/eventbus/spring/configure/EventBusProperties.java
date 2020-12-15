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

        private String group;
        private String topic;
        private String type;

        private SelectorType selectorType = SelectorType.TAG;

        private String selectorExpression = "*";

        private ConsumeMode consumeMode = ConsumeMode.CONCURRENTLY;

        private MessageModel messageModel = MessageModel.CLUSTERING;

        private int consumeThreadMax = 64;

        private long consumeTimeout = 30000L;

        private String accessKey = ACCESS_KEY_PLACEHOLDER;

        private String secretKey = SECRET_KEY_PLACEHOLDER;

        private boolean enableMsgTrace = true;

        private String customizedTraceTopic = TRACE_TOPIC_PLACEHOLDER;

        private String nameServer = NAME_SERVER_PLACEHOLDER;

        private String accessChannel = ACCESS_CHANNEL_PLACEHOLDER;

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

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public SelectorType getSelectorType() {
            return selectorType;
        }

        public void setSelectorType(SelectorType selectorType) {
            this.selectorType = selectorType;
        }

        public String getSelectorExpression() {
            return selectorExpression;
        }

        public void setSelectorExpression(String selectorExpression) {
            this.selectorExpression = selectorExpression;
        }

        public ConsumeMode getConsumeMode() {
            return consumeMode;
        }

        public void setConsumeMode(ConsumeMode consumeMode) {
            this.consumeMode = consumeMode;
        }

        public MessageModel getMessageModel() {
            return messageModel;
        }

        public void setMessageModel(MessageModel messageModel) {
            this.messageModel = messageModel;
        }

        public int getConsumeThreadMax() {
            return consumeThreadMax;
        }

        public void setConsumeThreadMax(int consumeThreadMax) {
            this.consumeThreadMax = consumeThreadMax;
        }

        public long getConsumeTimeout() {
            return consumeTimeout;
        }

        public void setConsumeTimeout(long consumeTimeout) {
            this.consumeTimeout = consumeTimeout;
        }

        public String getAccessKey() {
            return accessKey;
        }

        public void setAccessKey(String accessKey) {
            this.accessKey = accessKey;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public boolean isEnableMsgTrace() {
            return enableMsgTrace;
        }

        public void setEnableMsgTrace(boolean enableMsgTrace) {
            this.enableMsgTrace = enableMsgTrace;
        }

        public String getCustomizedTraceTopic() {
            return customizedTraceTopic;
        }

        public void setCustomizedTraceTopic(String customizedTraceTopic) {
            this.customizedTraceTopic = customizedTraceTopic;
        }

        public String getNameServer() {
            return nameServer;
        }

        public void setNameServer(String nameServer) {
            this.nameServer = nameServer;
        }

        public String getAccessChannel() {
            return accessChannel;
        }

        public void setAccessChannel(String accessChannel) {
            this.accessChannel = accessChannel;
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
