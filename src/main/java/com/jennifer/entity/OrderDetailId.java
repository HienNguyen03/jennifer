package com.jennifer.entity;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
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
}
