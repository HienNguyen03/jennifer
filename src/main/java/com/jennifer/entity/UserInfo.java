package com.jennifer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles users' related activities
 */

@Entity
@Table(name = "user_info")
public class UserInfo {

    public static final String EMAIL_PATTERN = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";

    public enum Role {
        CUSTOMER, MANAGER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "FULLNAME", nullable = false, length = 100)
    private String fullname;

    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false, length = 60)
    private Role role;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userInfo")
    private List<ShippingAddress> shippingAddresses = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userInfo")
    private List<OrderInfo> orderInfos = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "userInfo")
    private FavoriteBag favoriteBag;

    @JsonIgnore
    @OneToOne(mappedBy = "userInfo")
    private ShoppingBag shoppingBag;

    @JsonIgnore
    @OneToMany(mappedBy = "primaryKey.userInfo", cascade = CascadeType.ALL)
    private List<ViewedProduct> viewedProducts = new ArrayList<>();

    public UserInfo() {
    }

    public boolean isCustomer() {
        return role.equals(Role.CUSTOMER);
    }

    public boolean isManager() {
        return role.equals(Role.MANAGER);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<ShippingAddress> getShippingAddresses() {
        return shippingAddresses;
    }

    public void setShippingAddresses(List<ShippingAddress> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }

    public List<OrderInfo> getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(List<OrderInfo> orderInfos) {
        this.orderInfos = orderInfos;
    }

    public FavoriteBag getFavoriteBag() {
        return favoriteBag;
    }

    public void setFavoriteBag(FavoriteBag favoriteBag) {
        this.favoriteBag = favoriteBag;
    }

    public ShoppingBag getShoppingBag() {
        return shoppingBag;
    }

    public void setShoppingBag(ShoppingBag shoppingBag) {
        this.shoppingBag = shoppingBag;
    }

    public List<ViewedProduct> getViewedProducts() {
        return viewedProducts;
    }

    public void setViewedProducts(List<ViewedProduct> viewedProducts) {
        this.viewedProducts = viewedProducts;
    }
}
