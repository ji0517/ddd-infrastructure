package com.xwtec.infrastructure.eventbus.spring.configure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xwtec.infrastructure.eventbus.spring.consume.DefaultEventListener;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.rocketmq.client.AccessChannel;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties;
import org.apache.rocketmq.spring.core.RocketMQListener;
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
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Configuration
public class ListenerContainerConfiguration implements ApplicationContextAware, SmartInitializingSingleton {
    private final static Logger log = LoggerFactory.getLogger(ListenerContainerConfiguration.class);

    private ConfigurableApplicationContext applicationContext;

    private AtomicLong counter = new AtomicLong(0);

    private AtomicLong listenerCounter = new AtomicLong(0);

    private StandardEnvironment environment;

    private RocketMQProperties rocketMQProperties;

//    private ObjectMapper objectMapper;

    private RocketMQMessageConverter rocketMQMessageConverter;

    private EventBusProperties eventBusProperties;

    public ListenerContainerConfiguration(RocketMQMessageConverter rocketMQMessageConverter,
                                          StandardEnvironment environment,
                                          RocketMQProperties rocketMQProperties,
                                          EventBusProperties eventBusProperties
    ) {
        this.rocketMQMessageConverter = rocketMQMessageConverter;
        this.environment = environment;
        this.rocketMQProperties = rocketMQProperties;
        this.eventBusProperties = eventBusProperties;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
    }

    @Override
    public void afterSingletonsInstantiated() {
//        Map<String, Object> beans = this.applicationContext.getBeansWithAnnotation(RocketMQMessageListener.class);
        List<EventBusProperties.ConsumerProperties> consumers = this.eventBusProperties.getConsumers();
        if (Objects.nonNull(consumers)) {
            consumers.forEach(this::registerContainer);
        }
    }

    private void registerContainer(EventBusProperties.ConsumerProperties consumerProperties) {
//        Class<?> clazz = AopProxyUtils.ultimateTargetClass(bean);
//
//        if (RocketMQListener.class.isAssignableFrom(bean.getClass()) && RocketMQReplyListener.class.isAssignableFrom(bean.getClass())) {
//            throw new IllegalStateException(clazz + " cannot be both instance of " + RocketMQListener.class.getName() + " and " + RocketMQReplyListener.class.getName());
//        }
//
//        if (!RocketMQListener.class.isAssignableFrom(bean.getClass()) && !RocketMQReplyListener.class.isAssignableFrom(bean.getClass())) {
//            throw new IllegalStateException(clazz + " is not instance of " + RocketMQListener.class.getName() + " or " + RocketMQReplyListener.class.getName());
//        }

        RocketMQMessageListener annotation = new RocketMQMessageListener() {
            @Override
            public String consumerGroup() {
                return consumerProperties.getGroup();
            }

            @Override
            public String topic() {
                return consumerProperties.getTopic();
            }

            @Override
            public SelectorType selectorType() {
                return consumerProperties.getSelectorType();
            }

            @Override
            public String selectorExpression() {
                return consumerProperties.getSelectorExpression();
            }

            @Override
            public ConsumeMode consumeMode() {
                return consumerProperties.getConsumeMode();
            }

            @Override
            public MessageModel messageModel() {
                return consumerProperties.getMessageModel();
            }

            @Override
            public int consumeThreadMax() {
                return consumerProperties.getConsumeThreadMax();
            }

            @Override
            public long consumeTimeout() {
                return consumerProperties.getConsumeTimeout();
            }

            @Override
            public String accessKey() {
                return consumerProperties.getAccessKey();
            }

            @Override
            public String secretKey() {
                return consumerProperties.getSecretKey();
            }

            @Override
            public boolean enableMsgTrace() {
                return consumerProperties.isEnableMsgTrace();
            }

            @Override
            public String customizedTraceTopic() {
                return consumerProperties.getCustomizedTraceTopic();
            }

            @Override
            public String nameServer() {
                return consumerProperties.getNameServer();
            }

            @Override
            public String accessChannel() {
                return consumerProperties.getAccessChannel();
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return RocketMQMessageListener.class;
            }
        };

        String consumerGroup = consumerProperties.getGroup();
        String topic = consumerProperties.getTopic();

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

        String containerBeanName = String.format("%s_%s", DefaultRocketMQListenerContainer.class.getName(),
                counter.incrementAndGet());
        GenericApplicationContext genericApplicationContext = (GenericApplicationContext) applicationContext;

//        String beanName = "RocketMQListener_" + consumerProperties.getType();

        Object bean = genericApplicationContext.getBean("RocketMQListener_Default",DefaultEventListener.class);

        String listenerBeanName = String.format("%s_%s", bean.getClass().getName(), listenerCounter.incrementAndGet());

        genericApplicationContext.registerBean(listenerBeanName, bean.getClass());

        genericApplicationContext.registerBean(containerBeanName, DefaultRocketMQListenerContainer.class,
                () -> createRocketMQListenerContainer(containerBeanName,
                        genericApplicationContext.getBean(listenerBeanName), annotation)
        );
        DefaultRocketMQListenerContainer container = genericApplicationContext.getBean(containerBeanName,
                DefaultRocketMQListenerContainer.class);
        if (!container.isRunning()) {
            try {
                container.start();
            } catch (Exception e) {
                log.error("Started container failed. {}", container, e);
                throw new RuntimeException(e);
            }
        }
        log.info("Register the listener to container, listenerBeanName:{}, containerBeanName:{}", listenerBeanName, containerBeanName);
    }

    private DefaultRocketMQListenerContainer createRocketMQListenerContainer(String name, Object bean,
                                                                             RocketMQMessageListener annotation) {
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
        container.setName(name);
        return container;
    }

    private void validate(RocketMQMessageListener annotation) {
        if (annotation.consumeMode() == ConsumeMode.ORDERLY &&
                annotation.messageModel() == MessageModel.BROADCASTING) {
            throw new BeanDefinitionValidationException(
                    "Bad annotation definition in @RocketMQMessageListener, messageModel BROADCASTING does not support ORDERLY message!");
        }
    }
}