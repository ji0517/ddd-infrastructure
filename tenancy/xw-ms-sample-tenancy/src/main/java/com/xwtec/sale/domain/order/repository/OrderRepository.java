package com.xwtec.sale.domain.order.repository;

import com.xwtec.sale.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, String> {

}
