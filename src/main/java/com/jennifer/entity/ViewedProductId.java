package com.jennifer.entity;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Composite Id class for the composite key
 */

@Embeddable
public class ViewedProductId implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    private ProductInfo productInfo;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserInfo userInfo;

    ProductInfo getProductInfo() {
        return productInfo;
    }

    void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    UserInfo getUserInfo() {
        return userInfo;
    }

    void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViewedProductId)) return false;

        ViewedProductId that = (ViewedProductId) o;

        if (!getProductInfo().equals(that.getProductInfo())) return false;
        return getUserInfo().equals(that.getUserInfo());
    }

    @Override
    public int hashCode() {
        int result = getProductInfo().hashCode();
        result = 31 * result + getUserInfo().hashCode();
        return result;
    }
}
