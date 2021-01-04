package com.xwtec.sale.domain.stock.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_stock")
@EntityListeners(AuditingEntityListener.class)
public class Stock {

    @Id
    @Column(name = "f_goodsid")
    private String goodsId;

    @Column(name = "f_goodsname")
    private String goodsName;

    @Column(name = "f_stocknum")
    private Integer stockNum;

    @Version
    @Column(name = "f_version")
    private Long version;

    @CreatedDate
    @Column(name = "f_createtime")
    private Date createTime;

    @CreatedDate
    @Column(name = "f_updatetime")
    private Date updateTime;


    public void subStockNum(Integer stockNum){
        //什么库存什么的就不考虑了
        this.stockNum-= stockNum;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
