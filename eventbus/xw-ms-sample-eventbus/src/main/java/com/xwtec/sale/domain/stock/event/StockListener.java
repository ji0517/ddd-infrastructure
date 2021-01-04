package com.xwtec.sale.domain.stock.event;

import com.xwtec.infrastructure.eventbus.spring.core.EventBusPayload;
import com.xwtec.sale.application.IAppService;
import com.xwtec.sale.domain.order.event.OrderCreated;
import com.xwtec.sale.domain.stock.service.IStockService;
import com.xwtec.sample.eventbus.controller.AccountCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class StockListener {

    @Autowired
    IStockService stockService;

    @EventListener(condition = "#payload.topic == 'SALESORDERCREATED'")
    @Async
    public void processOrderCreat(EventBusPayload payload) {
        EventBusPayload eventBusPayload = (EventBusPayload) payload;
        HashMap hashMap = (HashMap) eventBusPayload.getPayload();
        String goodsId = hashMap.get("goodsId").toString();
        Integer goodsNum = (Integer) hashMap.get("goodsNum");

        stockService.stockChange(goodsId,goodsNum);

    }

}
