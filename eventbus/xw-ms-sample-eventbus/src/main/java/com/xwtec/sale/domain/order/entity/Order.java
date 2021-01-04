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

    @Column(name = "f_transid")
    private String transId;

    @OneToOne
    @JoinColumn(name = "f_goodsid", referencedColumnName = "f_goodsid",insertable = false,updatable = false)
    private Goods goods;



    public static Order createOrder(String goodsId,Integer goodsNum,String transId){
        Order  order = new Order();
        String orderNum = UUID.randomUUID().toString();
        order.orderNum = orderNum;
        order.goodsId = goodsId;
        order.goodsNum = goodsNum;
        order.transId = transId;
        return order;
    }

    public Order getOrder(){
       return this;
    }

    public boolean checkOrder(String transId){
        if(this.transId.equals(transId) ){
            return true;
        }
        return false;
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

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
