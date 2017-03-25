package com.jennifer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Handles customers' order related information
 */

@Entity
@Table(name = "order_info")
public class OrderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ORDER_DATE", nullable = false)
    private Date orderDate;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserInfo userInfo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SHIPPING_ADDRESS_ID")
    private ShippingAddress shippingAddress;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DELIVERY_ID")
    private DeliveryMethod deliveryMethod;

    @Column(name = "STATUS", nullable = false, length = 45)
    private String status;

    @OneToOne(mappedBy = "orderInfo", fetch = FetchType.EAGER)
    private PaymentInvoice paymentInvoice;

    @JsonIgnore
    @OneToMany(mappedBy = "primaryKey.orderInfo", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public OrderInfo() {
    }

    public OrderInfo(Date orderDate, BigDecimal totalPrice, UserInfo userInfo, String status) {
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.userInfo = userInfo;
        this.status = status;
    }

    public OrderInfo(Date orderDate, BigDecimal totalPrice, UserInfo userInfo, ShippingAddress shippingAddress, DeliveryMethod deliveryMethod, String status) {
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.userInfo = userInfo;
        this.shippingAddress = shippingAddress;
        this.deliveryMethod = deliveryMethod;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PaymentInvoice getPaymentInvoice() {
        return paymentInvoice;
    }

    public void setPaymentInvoice(PaymentInvoice paymentInvoice) {
        this.paymentInvoice = paymentInvoice;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                '}';
    }
}
