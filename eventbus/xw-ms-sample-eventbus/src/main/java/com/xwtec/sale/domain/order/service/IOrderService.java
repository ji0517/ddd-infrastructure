package com.xwtec.sale.domain.order.service;

import com.xwtec.sale.domain.order.entity.Order;

import java.util.List;

public interface IOrderService {

    boolean createOrder(String goodsId,Integer goodsNum,String transId);

    Order getOrder(String orderNum);

    List<Order> getOrders();

    boolean checkOrder(String transId);

    boolean createOrderEvent(String goodsId,Integer goodsNum);
}
