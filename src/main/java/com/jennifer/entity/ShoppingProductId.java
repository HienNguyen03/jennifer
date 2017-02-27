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
    private ShoppingBag shoppingBag;

    ProductInfo getProductInfo() {
        return productInfo;
    }

    void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    ShoppingBag getShoppingBag() {
        return shoppingBag;
    }

    void setShoppingBag(ShoppingBag shoppingBag) {
        this.shoppingBag = shoppingBag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingProductId)) return false;

        ShoppingProductId that = (ShoppingProductId) o;

        if (!getProductInfo().equals(that.getProductInfo())) return false;
        return getShoppingBag().equals(that.getShoppingBag());
    }

    @Override
    public int hashCode() {
        int result = getProductInfo().hashCode();
        result = 31 * result + getShoppingBag().hashCode();
        return result;
    }
}
