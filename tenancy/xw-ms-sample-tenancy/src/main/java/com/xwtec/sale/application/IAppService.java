package com.xwtec.sale.application;

import com.xwtec.sale.domain.order.entity.Order;

import java.util.List;

public interface IAppService {

    boolean createOrder(String goodsId,Integer goodsNum);
}
