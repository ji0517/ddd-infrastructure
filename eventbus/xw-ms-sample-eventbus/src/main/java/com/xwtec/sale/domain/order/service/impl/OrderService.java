package com.xwtec.sale.domain.order.service.impl;

import com.xwtec.infrastructure.eventbus.spring.core.EventBusPayload;
import com.xwtec.infrastructure.eventbus.spring.produce.EventBusResult;
import com.xwtec.infrastructure.eventbus.spring.produce.IEventBus;
import com.xwtec.sale.domain.order.entity.Order;
import com.xwtec.sale.domain.order.event.OrderCreated;
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

    @Resource
    IEventBus eventBus;


    @Override
    public boolean createOrder(String goodsId, Integer goodsNum, String transId) {
        try {
            Order order = Order.createOrder(goodsId, goodsNum, transId);
            order = orderRepository.save(order);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Order getOrder(String orderNum) {
        Optional<Order> optionalOrder = orderRepository.findById(orderNum);
        if (optionalOrder.isPresent()) {
            return optionalOrder.get();
        }
        return null;
    }

    @Override
    public List<Order> getOrders() {
        List<Order> orderList = orderRepository.findAll();
        return orderList;
    }

    @Override
    public boolean checkOrder(String transId) {
        Order order = orderRepository.findByTransId(transId);
        if (order != null) {
            return order.checkOrder(transId);
        }
        return false;
    }

    @Override
    public boolean createOrderEvent(String goodsId, Integer goodsNum) {

        OrderCreated orderCreated = new OrderCreated();
        orderCreated.setGoodsId(goodsId);
        orderCreated.setGoodsNum(goodsNum);

        EventBusPayload eventBusPayload2 = EventBusPayload.transaction(
                "SALESORDERCREATED", orderCreated);
        EventBusResult eventBusResult =  eventBus.post(eventBusPayload2);
        if(eventBusResult.getCode().equals(EventBusResult.OK)){
            return true;
        }
        return false;
    }
}
