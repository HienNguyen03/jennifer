package com.jennifer.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.omg.CORBA.ServerRequest;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Handles users' shipping address
 */

@Entity
@Table(name = "shipping_address")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope = ShippingAddress.class)
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "RECIPIENT", nullable = false, length = 100)
    private String recipient;

    @Column(name = "ADDRESS", nullable = false, length = 120)
    private String address;

    @Column(name = "CITY", nullable = false, length = 100)
    private String city;

    @Column(name = "POSTAL_CODE", nullable = false, length = 10)
    private String postalCode;

    //@JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private UserInfo userInfo;

    @JsonIgnore
    @OneToMany(mappedBy = "shippingAddress", cascade = CascadeType.ALL)
    private List<OrderInfo> orderInfos;

    public ShippingAddress() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<OrderInfo> getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(List<OrderInfo> orderInfos) {
        this.orderInfos = orderInfos;
    }

    @Override
    public String toString() {
        return "ShippingAddress{" +
                "id=" + id +
                ", recipient='" + recipient + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
