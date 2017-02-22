package com.jennifer.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Handles detailed products inside each customers' shopping bag
 */

@Entity
@Table(name = "shopping_product")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.productInfo", joinColumns = @JoinColumn(name = "PRODUCT_ID")),
        @AssociationOverride(name = "primaryKey.shoppingBag", joinColumns = @JoinColumn(name = "SHOPPING_PRODUCT_ID"))
})
public class ShoppingProduct {

    // composite id key
    @EmbeddedId
    private ShoppingProductId primaryKey;

    @Column(name = "UNIT_PRICE", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "APPLIED_DISCOUNT")
    private int appliedDiscount;

    private ShoppingProductId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ShoppingProductId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public ProductInfo getProductInfo(){
        return getPrimaryKey().getProductInfo();
    }

    public void setProductInfo(ProductInfo productInfo){
        getPrimaryKey().setProductInfo(productInfo);
    }

    @Transient
    public ShoppingBag getShoppingBag(){
        return getPrimaryKey().getShoppingBag();
    }

    public void setShoppingBag(ShoppingBag shoppingBag){
        getPrimaryKey().setShoppingBag(shoppingBag);
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
}
