package com.xwtec.sale.domain.order.service;

import com.xwtec.sale.domain.order.entity.Order;

import java.util.List;

public interface IOrderService {

    boolean createOrder(String goodsId,Integer goodsNum);

    Order getOrder(String orderNum);
}
