package com.xwtec.sale.application.impl;

import com.xwtec.sale.application.IAppService;
import com.xwtec.sale.domain.order.entity.Order;
import com.xwtec.sale.domain.order.service.IOrderService;
import com.xwtec.sale.domain.stock.entity.Stock;
import com.xwtec.sale.domain.stock.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService implements IAppService {

    @Autowired
    IOrderService orderService;

    @Autowired
    IStockService stockService;

    @Override
    public boolean createOrder(String goodsId, Integer goodsNum) {
        return orderService.createOrderEvent(goodsId,goodsNum);
    }

    @Override
    public Order getOrder(String orderNum) {
        return orderService.getOrder(orderNum);
    }

    @Override
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @Override
    public boolean stockChange(String goodsId, Integer stockNum) {
        return stockService.stockChange(goodsId,stockNum);
    }

    @Override
    public Stock getStock(String goodsId) {
        return stockService.getStock(goodsId);
    }
}
