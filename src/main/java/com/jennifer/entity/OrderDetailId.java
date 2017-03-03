package com.jennifer.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Composite Id class for the composite key
 */

@Embeddable
class OrderDetailId implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    private OrderInfo orderInfo;

    @ManyToOne(cascade = CascadeType.ALL)
    private ProductInfo productInfo;

    OrderInfo getOrderInfo() {
        return orderInfo;
    }

    void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    ProductInfo getProductInfo() {
        return productInfo;
    }

    void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetailId)) return false;

        OrderDetailId that = (OrderDetailId) o;

        if (!getOrderInfo().equals(that.getOrderInfo())) return false;
        return getProductInfo().equals(that.getProductInfo());
    }

    @Override
    public int hashCode() {
        int result = getOrderInfo().hashCode();
        result = 31 * result + getProductInfo().hashCode();
        return result;
    }
}
