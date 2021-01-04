package com.xwtec.sale.domain.stock.service;

import com.xwtec.sale.domain.stock.entity.Stock;

public interface IStockService {

    boolean stockChange(String goodsId,Integer stockNum);

    Stock getStock(String goodsId);

}
