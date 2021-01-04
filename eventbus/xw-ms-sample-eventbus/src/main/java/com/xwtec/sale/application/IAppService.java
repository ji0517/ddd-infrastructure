package com.xwtec.sale.application;

import com.xwtec.sale.domain.order.entity.Order;
import com.xwtec.sale.domain.stock.entity.Stock;

import java.util.List;

public interface IAppService {

    boolean createOrder(String goodsId, Integer goodsNum);

    Order getOrder(String orderNum);

    List<Order> getOrders();

    boolean stockChange(String goodsId,Integer stockNum);

    Stock getStock(String goodsId);
}
