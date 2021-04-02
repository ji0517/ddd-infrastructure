package com.xwtec.sale.domain.order.service.impl;

import com.xwtec.sale.domain.order.entity.Order;
import com.xwtec.sale.domain.order.repository.OrderRepository;
import com.xwtec.sale.domain.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    OrderRepository orderRepository;



    @Override
    public boolean createOrder(String goodsId, Integer goodsNum) {
        try {
            Order order = Order.createOrder(goodsId, goodsNum);
            order = orderRepository.save(order);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Order getOrder(String orderNum) {
        return null;
    }
}
