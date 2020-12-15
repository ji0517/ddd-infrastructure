package com.xwtec.sample.eventbus.controller;

import com.xwtec.infrastructure.eventbus.spring.produce.EventBus;
import com.xwtec.infrastructure.eventbus.spring.produce.IEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {


    @Resource
    IEventBus eventBus;

//    @Resource
//    private RocketMQTemplate rocketMQTemplate;

    @GetMapping
    ResponseEntity<List> get(String id) {

        List<String> strings = new ArrayList<>() ;
        strings.add("1");

//        rocketMQTemplate.

        eventBus.post(new AccountCreatedEvent("account"));

//        publisher.publishEvent(new AccountCreatedEvent("account"));

        return ResponseEntity.ok(strings);
    }
}