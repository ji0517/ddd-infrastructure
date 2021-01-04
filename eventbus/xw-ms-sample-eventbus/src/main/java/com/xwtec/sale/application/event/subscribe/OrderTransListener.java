package com.xwtec.sale.application.event.subscribe;

import com.xwtec.infrastructure.eventbus.spring.core.EventBusPayload;
import com.xwtec.infrastructure.eventbus.spring.produce.listener.ILocalTransaction;
import com.xwtec.sale.application.IAppService;
import com.xwtec.sale.domain.order.event.OrderCreated;
import com.xwtec.sale.domain.order.service.IOrderService;
import com.xwtec.sample.eventbus.controller.AccountListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component("SALESORDERCREATEDLocalTransaction")
public class OrderTransListener implements ILocalTransaction {
    private static final Logger log = LoggerFactory.getLogger(OrderTransListener.class);

    @Autowired
    IOrderService orderService;

//    List<String> localTrans = new ArrayList<>();


    @Override
    public void executeLocalTransaction(String transactionId, Object payload) {

        log.info("executeLocalTransaction进了");
        EventBusPayload eventBusPayload = (EventBusPayload) payload;
        OrderCreated orderCreated = (OrderCreated)eventBusPayload.getPayload();
//        HashMap hashMap = (HashMap) eventBusPayload.getPayload();
//        String goodsId = hashMap.get("goodsId").toString();
//        Integer goodsNum = (Integer) hashMap.get("goodsNum");

        orderService.createOrder(orderCreated.getGoodsId(), orderCreated.getGoodsNum(), transactionId);
//        localTrans.add(transactionId);
    }

    @Override
    public boolean checkLocalTransaction(String transactionId) {
        log.info("checkLocalTransaction进了");
        return orderService.checkOrder(transactionId);
//        return localTrans.contains(transactionId);
    }
}
