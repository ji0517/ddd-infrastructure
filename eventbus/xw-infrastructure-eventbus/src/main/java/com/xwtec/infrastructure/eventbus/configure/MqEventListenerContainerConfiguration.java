package com.xwtec.infrastructure.eventbus.configure;

import java.lang.annotation.Annotation;
import java.util.Collections;

import com.xwtec.infrastructure.eventbus.consume.MqEventSubscriber;
import org.apache.rocketmq.client.AccessChannel;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQReplyListener;
import org.apache.rocketmq.spring.support.DefaultRocketMQListenerContainer;
import org.apache.rocketmq.spring.support.RocketMQMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.util.StringUtils;

/**
 *<p>
 *
 *</p>
 *
 */
public class MqEventListenerContainerConfiguration implements ApplicationContextAware, SmartInitializingSingleton {

    private static final Logger log = LoggerFactory.getLogger(MqEventListenerContainerConfiguration.class);

    private final static String CONTAINER_NAME = "DefaultRocketMQListenerContainer_MQ";

    private ConfigurableApplicationContext applicationContext;

    private StandardEnvironment environment;

    private EventBusProperties eventBusProperties;

    private RocketMQProperties rocketMQProperties;

    private RocketMQMessageConverter rocketMQMessageConverter;

    private RocketMQListener bean;

    private RocketMQMessageListener annotation;

    public MqEventListenerContainerConfiguration(StandardEnvironment environment,
        EventBusProperties eventBusProperties,
        RocketMQProperties rocketMQProperties,
        RocketMQMessageConverter rocketMQMessageConverter) {
        this.environment = environment;
        this.eventBusProperties = eventBusProperties;
        this.rocketMQProperties = rocketMQProperties;
        this.rocketMQMessageConverter = rocketMQMessageConverter;

        this.bean = new MqEventSubscriber();
        this.annotation = rocketMQMessageListener();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
    }

    @Override
    public void afterSingletonsInstantiated() {
        registerContainer();
    }

    private void registerContainer() {
        Class<?> clazz = AopProxyUtils.ultimateTargetClass(bean);

        if (RocketMQListener.class.isAssignableFrom(bean.getClass()) && RocketMQReplyListener.class
            .isAssignableFrom(bean.getClass())) {
            throw new IllegalStateException(
                clazz + " cannot be both instance of " + RocketMQListener.class.getName() + " and "
                    + RocketMQReplyListener.class.getName());
        }

        if (!RocketMQListener.class.isAssignableFrom(bean.getClass()) && !RocketMQReplyListener.class
            .isAssignableFrom(bean.getClass())) {
            throw new IllegalStateException(
                clazz + " is not instance of " + RocketMQListener.class.getName() + " or " + RocketMQReplyListener.class
                    .getName());
        }

        String consumerGroup = this.environment.resolvePlaceholders(annotation.consumerGroup());
        String topic = this.environment.resolvePlaceholders(annotation.topic());

        boolean listenerEnabled =
            (boolean) rocketMQProperties.getConsumer().getListeners().getOrDefault(consumerGroup, Collections.EMPTY_MAP)
                .getOrDefault(topic, true);

        if (!listenerEnabled) {
            log.debug(
                "Consumer Listener (group:{},topic:{}) is not enabled by configuration, will ignore initialization.",
                consumerGroup, topic);
            return;
        }
        validate(annotation);

        GenericApplicationContext genericApplicationContext = (GenericApplicationContext) applicationContext;

        genericApplicationContext.registerBean(CONTAINER_NAME, DefaultRocketMQListenerContainer.class,
            () -> createRocketMQListenerContainer());
        DefaultRocketMQListenerContainer container = genericApplicationContext.getBean(CONTAINER_NAME,
            DefaultRocketMQListenerContainer.class);
        if (!container.isRunning()) {
            try {
                container.start();
            } catch (Exception e) {
                log.error("Started container failed. {}", container, e);
                throw new RuntimeException(e);
            }
        }
    }

    private DefaultRocketMQListenerContainer createRocketMQListenerContainer() {
        DefaultRocketMQListenerContainer container = new DefaultRocketMQListenerContainer();

        container.setRocketMQMessageListener(annotation);

        String nameServer = environment.resolvePlaceholders(annotation.nameServer());
        nameServer = StringUtils.isEmpty(nameServer) ? rocketMQProperties.getNameServer() : nameServer;
        String accessChannel = environment.resolvePlaceholders(annotation.accessChannel());
        container.setNameServer(nameServer);
        if (!StringUtils.isEmpty(accessChannel)) {
            container.setAccessChannel(AccessChannel.valueOf(accessChannel));
        }
        container.setTopic(environment.resolvePlaceholders(annotation.topic()));
        String tags = environment.resolvePlaceholders(annotation.selectorExpression());
        if (!StringUtils.isEmpty(tags)) {
            container.setSelectorExpression(tags);
        }
        container.setConsumerGroup(environment.resolvePlaceholders(annotation.consumerGroup()));
        if (RocketMQListener.class.isAssignableFrom(bean.getClass())) {
            container.setRocketMQListener((RocketMQListener) bean);
        } else if (RocketMQReplyListener.class.isAssignableFrom(bean.getClass())) {
            container.setRocketMQReplyListener((RocketMQReplyListener) bean);
        }
        container.setMessageConverter(rocketMQMessageConverter.getMessageConverter());
        container.setName(CONTAINER_NAME);

        return container;
    }

    private RocketMQMessageListener rocketMQMessageListener() {
        return new RocketMQMessageListener() {
            @Override
            public String consumerGroup() {
                return eventBusProperties.getGroup();
            }

            @Override
            public String topic() {
                return eventBusProperties.getTopic();
            }

            @Override
            public SelectorType selectorType() {
                return eventBusProperties.getSelectorType();
            }

            @Override
            public String selectorExpression() {
                return eventBusProperties.getSelectorExpression();
            }

            @Override
            public ConsumeMode consumeMode() {
                return eventBusProperties.getConsumeMode();
            }

            @Override
            public MessageModel messageModel() {
                return eventBusProperties.getMessageModel();
            }

            @Override
            public int consumeThreadMax() {
                return eventBusProperties.getConsumeThreadMax();
            }

            @Override
            public long consumeTimeout() {
                return eventBusProperties.getConsumeTimeout();
            }

            @Override
            public String accessKey() {
                return eventBusProperties.getAccessKey();
            }

            @Override
            public String secretKey() {
                return eventBusProperties.getSecretKey();
            }

            @Override
            public boolean enableMsgTrace() {
                return eventBusProperties.isEnableMsgTrace();
            }

            @Override
            public String customizedTraceTopic() {
                return eventBusProperties.getCustomizedTraceTopic();
            }

            @Override
            public String nameServer() {
                return eventBusProperties.getNameServer();
            }

            @Override
            public String accessChannel() {
                return eventBusProperties.getAccessChannel();
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return RocketMQMessageListener.class;
            }
        };
    }

    private void validate(RocketMQMessageListener annotation) {
        if (annotation.consumeMode() == ConsumeMode.ORDERLY &&
            annotation.messageModel() == MessageModel.BROADCASTING) {
            throw new BeanDefinitionValidationException(
                "Bad annotation definition in @RocketMQMessageListener, messageModel BROADCASTING does not support ORDERLY message!");
        }
    }
}
