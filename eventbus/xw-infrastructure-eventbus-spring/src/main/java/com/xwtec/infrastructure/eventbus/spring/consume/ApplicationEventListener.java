package com.xwtec.infrastructure.eventbus.spring.consume;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;


public class ApplicationEventListener implements ApplicationListener<ApplicationEvent> {

    private static final Logger log = LoggerFactory.getLogger(ApplicationEventListener.class);


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        Object payload = ((PayloadApplicationEvent)event).getPayload();
        String interfaces = payload.getClass().getInterfaces().toString();
        log.info("ApplicationEventListener:{},{}",interfaces,payload.toString());
    }
}
