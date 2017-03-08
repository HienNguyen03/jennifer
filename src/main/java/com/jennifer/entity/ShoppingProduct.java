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
        @AssociationOverride(name = "primaryKey.userInfo", joinColumns = @JoinColumn(name = "USER_ID"))
})
public class ShoppingProduct {

    // composite id key
    @EmbeddedId
    private ShoppingProductId primaryKey = new ShoppingProductId();

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    public ShoppingProduct() {
    }

    public ShoppingProduct(UserInfo userInfo, ProductInfo productInfo, int quantity) {
        this.getPrimaryKey().setUserInfo(userInfo);
        this.getPrimaryKey().setProductInfo(productInfo);
        this.quantity = quantity;
    }

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
    public UserInfo getUserInfo(){
        return getPrimaryKey().getUserInfo();
    }

    public void setUserInfo(UserInfo userInfo){
        getPrimaryKey().setUserInfo(userInfo);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
