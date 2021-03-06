package com.jennifer.entity;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Composite Id class for the composite key
 */

@Embeddable
class ShoppingProductId implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    private ProductInfo productInfo;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserInfo userInfo;

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingProductId)) return false;

        ShoppingProductId that = (ShoppingProductId) o;

        if (getProductInfo() != null ? !getProductInfo().equals(that.getProductInfo()) : that.getProductInfo() != null)
            return false;
        return getUserInfo() != null ? getUserInfo().equals(that.getUserInfo()) : that.getUserInfo() == null;
    }

    @Override
    public int hashCode() {
        int result = getProductInfo() != null ? getProductInfo().hashCode() : 0;
        result = 31 * result + (getUserInfo() != null ? getUserInfo().hashCode() : 0);
        return result;
    }
}
