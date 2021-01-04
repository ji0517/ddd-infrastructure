package com.xwtec.sale.domain.stock.service.impl;

import com.xwtec.sale.domain.stock.entity.Stock;
import com.xwtec.sale.domain.stock.repository.StockRepository;
import com.xwtec.sale.domain.stock.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService implements IStockService {

    @Autowired
    StockRepository stockRepository;

    @Override
    public boolean stockChange(String goodsId, Integer stockNum) {
        Stock stock = this.getStock(goodsId);
        if (stock != null) {
            stock.subStockNum(stockNum);
            try {
                stockRepository.save(stock);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public Stock getStock(String goodsId) {
        Optional<Stock> stockOptional = stockRepository.findById(goodsId);
        if (stockOptional.isPresent()) {
            Stock stock = stockOptional.get();
            return stock;
        }
        return null;
    }
}
