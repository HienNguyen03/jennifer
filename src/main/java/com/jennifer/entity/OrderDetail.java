package com.jennifer.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Handles detailed products inside each customers' order
 */

@Entity
@Table(name = "order_detail")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.orderInfo", joinColumns = @JoinColumn(name = "ORDER_ID")),
        @AssociationOverride(name = "primaryKey.productInfo", joinColumns = @JoinColumn(name = "PRODUCT_ID"))
})
public class OrderDetail {

    // composite id key
    @EmbeddedId
    private OrderDetailId primaryKey = new OrderDetailId();

    @Column(name = "UNIT_PRICE", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "APPLIED_DISCOUNT")
    private int appliedDiscount;

    private OrderDetailId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(OrderDetailId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public OrderInfo getOrderInfo() {
        return getPrimaryKey().getOrderInfo();
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        getPrimaryKey().setOrderInfo(orderInfo);
    }

    @Transient
    public ProductInfo getProductInfo() {
        return getPrimaryKey().getProductInfo();
    }

    public void setProductInfo(ProductInfo productInfo) {
        getPrimaryKey().setProductInfo(productInfo);
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAppliedDiscount() {
        return appliedDiscount;
    }

    public void setAppliedDiscount(int appliedDiscount) {
        this.appliedDiscount = appliedDiscount;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "primaryKey=" + primaryKey +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", appliedDiscount=" + appliedDiscount +
                '}';
    }
}
