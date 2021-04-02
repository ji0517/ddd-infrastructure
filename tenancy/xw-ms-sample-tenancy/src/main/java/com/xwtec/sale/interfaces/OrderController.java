package com.xwtec.sale.interfaces;

import com.xwtec.sale.application.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    IAppService appService;

    @GetMapping("createOrder")
    public ResponseEntity<String> CreateOrder(String goodsId, Integer goodsNum){
       boolean result = appService.createOrder(goodsId,goodsNum);
       if(result){
           return ResponseEntity.ok("创建订单成功");
       }

        return ResponseEntity.ok("创建订单失败");
    }


    @GetMapping("createOrder1")
    public ResponseEntity<String> CreateOrder1(String goodsId, Integer goodsNum){
        boolean result = appService.createOrder(goodsId,goodsNum);
        if(result){
            return ResponseEntity.ok("创建订单成功");
        }

        return ResponseEntity.ok("创建订单失败");
    }


}
