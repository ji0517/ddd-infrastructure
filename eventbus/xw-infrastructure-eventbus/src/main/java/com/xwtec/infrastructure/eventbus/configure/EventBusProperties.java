package com.xwtec.infrastructure.eventbus.configure;

import static org.apache.rocketmq.spring.annotation.RocketMQMessageListener.ACCESS_CHANNEL_PLACEHOLDER;
import static org.apache.rocketmq.spring.annotation.RocketMQMessageListener.ACCESS_KEY_PLACEHOLDER;
import static org.apache.rocketmq.spring.annotation.RocketMQMessageListener.NAME_SERVER_PLACEHOLDER;
import static org.apache.rocketmq.spring.annotation.RocketMQMessageListener.SECRET_KEY_PLACEHOLDER;
import static org.apache.rocketmq.spring.annotation.RocketMQMessageListener.TRACE_TOPIC_PLACEHOLDER;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *<p>
 *
 *</p>
 *
 */
@ConfigurationProperties(prefix = "xw.eventbus.rocketmq")
public class EventBusProperties {

    private String group = "GID_xw_eventbus";

    private String topic = "Topic_xw_eventbus";

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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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
