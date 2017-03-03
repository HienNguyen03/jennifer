package com.jennifer.entity;

import javax.persistence.*;

/**
 * Saved all products that customer used to pay attention to.
 */

@Entity
@Table(name = "viewed_product")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.productInfo", joinColumns = @JoinColumn(name = "PRODUCT_ID")),
        @AssociationOverride(name = "primaryKey.userInfo", joinColumns = @JoinColumn(name = "USER_ID"))
})
public class ViewedProduct {

    // composite id key
    @EmbeddedId
    private ViewedProductId primaryKey = new ViewedProductId();

    @Column(name = "NO_OF_TIMES")
    private int noOfTimes;

    private ViewedProductId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ViewedProductId primaryKey) {
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

    public int getNoOfTimes() {
        return noOfTimes;
    }

    public void setNoOfTimes(int noOfTimes) {
        this.noOfTimes = noOfTimes;
    }

}
