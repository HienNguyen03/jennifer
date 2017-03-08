package com.jennifer.entity;

import javax.persistence.*;

/**
 * Handles detailed products inside each customers' favorite bag
 */

@Entity
@Table(name = "favorite_product")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.userInfo", joinColumns = @JoinColumn(name = "USER_ID")),
        @AssociationOverride(name = "primaryKey.productInfo", joinColumns = @JoinColumn(name = "PRODUCT_ID"))
})
public class FavoriteProduct {

    // composite id key
    @EmbeddedId
    private FavoriteProductId primaryKey = new FavoriteProductId();

    public FavoriteProduct() {
    }

    private FavoriteProductId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(FavoriteProductId primaryKey) {
        this.primaryKey = primaryKey;
    }

    public FavoriteProduct(UserInfo userInfo, ProductInfo productInfo) {
        this.primaryKey.setUserInfo(userInfo);
        this.primaryKey.setProductInfo(productInfo);
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

    @Override
    public String toString() {
        return "FavoriteProduct{" +
                "UserInfo=" + primaryKey.getUserInfo().toString() + '\'' +
                "ProductInfo=" + primaryKey.getProductInfo().toString() +
                '}';
    }
}
