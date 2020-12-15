package com.xwtec.infrastructure.eventbus.spring.configure;

import com.xwtec.infrastructure.eventbus.spring.consume.ApplicationEventListener;
import org.apache.rocketmq.client.AccessChannel;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties;
import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties.Producer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQMessageConverter;
import org.apache.rocketmq.spring.support.RocketMQUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 *<p>
 *
 *</p>
 *
 */
@Configuration
@EnableConfigurationProperties(EventBusProperties.class)
@Import({ExceutorConfiguration.class,ListenerContainerConfiguration.class})
@ComponentScan({"com.xwtec.infrastructure.eventbus.spring.produce.**","com.xwtec.infrastructure.eventbus.spring.consume.**"})
public class EventBusAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(EventBusAutoConfiguration.class);

    @Resource
    private StandardEnvironment environment;

    @Resource
    private EventBusProperties eventBusProperties;

    @Resource
    private RocketMQProperties rocketMQProperties;

    @Resource
    private RocketMQMessageConverter rocketMQMessageConverter;


    @Bean
    public ApplicationEventListener mqEventBus()  {
        ApplicationEventListener applicationEventListener = new ApplicationEventListener();
        return applicationEventListener;
    }

//    @Bean
//    public RocketMQTemplate mqEventBus()  {
//        log.info("RocketMQTemplate");
//
//        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate();
//
//
//        return rocketMQTemplate;
//    }


//    @Bean
//    public MqEventBus mqEventBus() throws MQClientException {
//        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate();
//        rocketMQTemplate.setProducer(mqProducer(rocketMQProperties, eventBusProperties));
//        rocketMQTemplate.setMessageConverter(rocketMQMessageConverter.getMessageConverter());
//
//        return new MqEventBus(rocketMQTemplate, eventBusProperties);
//    }

//    @Bean
//    public MqEventListenerContainerConfiguration mqEventListenerContainerConfiguration() {
//        return new MqEventListenerContainerConfiguration(
//            environment, eventBusProperties,
//            rocketMQProperties, rocketMQMessageConverter);
//    }

//    public DefaultMQProducer mqProducer(RocketMQProperties rocketMQProperties, EventBusProperties eventBusProperties) throws MQClientException {
//        Producer producerConfig = rocketMQProperties.getProducer();
//        String nameServer = rocketMQProperties.getNameServer();
//        String groupName = eventBusProperties.getGroup();
//        Assert.hasText(nameServer, "[rocketmq.name-server] must not be null");
//        Assert.hasText(groupName, "[rocketmq.producer.group] must not be null");
//        String accessChannel = rocketMQProperties.getAccessChannel();
//        String ak = rocketMQProperties.getProducer().getAccessKey();
//        String sk = rocketMQProperties.getProducer().getSecretKey();
//        boolean isEnableMsgTrace = rocketMQProperties.getProducer().isEnableMsgTrace();
//        String customizedTraceTopic = rocketMQProperties.getProducer().getCustomizedTraceTopic();
//        DefaultMQProducer producer = RocketMQUtil
//            .createDefaultMQProducer(groupName, ak, sk, isEnableMsgTrace, customizedTraceTopic);
//        producer.setNamesrvAddr(nameServer);
//        if (!StringUtils.isEmpty(accessChannel)) {
//            producer.setAccessChannel(AccessChannel.valueOf(accessChannel));
//        }
//
//        producer.setSendMsgTimeout(producerConfig.getSendMessageTimeout());
//        producer.setRetryTimesWhenSendFailed(producerConfig.getRetryTimesWhenSendFailed());
//        producer.setRetryTimesWhenSendAsyncFailed(producerConfig.getRetryTimesWhenSendAsyncFailed());
//        producer.setMaxMessageSize(producerConfig.getMaxMessageSize());
//        producer.setCompressMsgBodyOverHowmuch(producerConfig.getCompressMessageBodyThreshold());
//        producer.setRetryAnotherBrokerWhenNotStoreOK(producerConfig.isRetryNextServer());
//        producer.start();
//        return producer;
//    }

}
