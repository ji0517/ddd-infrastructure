package com.xwtec.sale.domain.order.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "t_order")
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @Column(name = "f_ordernum")
    private String orderNum;

    @Column(name = "f_goodsid")
    private String goodsId;

    @Column(name = "f_goodsnum")
    private Integer goodsNum;

    @Version
    @Column(name = "f_version")
    private Long version;

    @CreatedDate
    @Column(name = "f_createtime")
    private Date createTime;




    public static Order createOrder(String goodsId,Integer goodsNum){
        Order  order = new Order();
        String orderNum = UUID.randomUUID().toString();
        order.orderNum = orderNum;
        order.goodsId = goodsId;
        order.goodsNum = goodsNum;
        return order;
    }

    public Order getOrder(){
       return this;
    }



    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
