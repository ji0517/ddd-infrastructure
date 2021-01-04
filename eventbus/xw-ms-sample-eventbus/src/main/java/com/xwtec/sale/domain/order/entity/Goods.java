package com.xwtec.sale.domain.order.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "t_goods")
@EntityListeners(AuditingEntityListener.class)
public class Goods {

    @Id
    @Column(name = "f_goodsid")
    private String goodsId;

    @Column(name = "f_goodsname")
    private String goodsName;
}
