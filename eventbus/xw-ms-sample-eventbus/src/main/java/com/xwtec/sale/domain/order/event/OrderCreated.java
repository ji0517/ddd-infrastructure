package com.xwtec.sale.domain.order.event;

import java.util.Date;

public class OrderCreated {

    private Integer goodsNum;

    private String goodsId;



    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}
