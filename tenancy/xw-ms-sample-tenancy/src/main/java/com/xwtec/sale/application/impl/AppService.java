package com.xwtec.sale.application.impl;

import com.xwtec.infrastructure.tenancy.web.context.TenantContextHolder;
import com.xwtec.sale.application.IAppService;
import com.xwtec.sale.domain.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppService implements IAppService {

    @Autowired
    IOrderService orderService;

    @Override
    @Transactional
    public boolean createOrder(String goodsId, Integer goodsNum) {

        if(TenantContextHolder.getTenant().equals("2")){
            throw new RuntimeException("createOrder 抛异常了");
        }

        return orderService.createOrder(goodsId,goodsNum);
    }
}
