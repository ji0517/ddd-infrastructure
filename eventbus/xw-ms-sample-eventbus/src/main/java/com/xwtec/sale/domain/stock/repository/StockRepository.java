package com.xwtec.sale.domain.stock.repository;

import com.xwtec.sale.domain.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StockRepository extends JpaRepository<Stock, String> {
}
