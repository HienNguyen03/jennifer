package com.jennifer.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles users' related activities
 */

@Entity
@Table(name = "user_info")
public class UserInfo {

    public enum Role {
        CUSTOMER, MANAGER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userInfo")
    private List<ShippingAddress> shippingAddresses = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userInfo")
    private List<OrderInfo> orderInfos = new ArrayList<>();

    @OneToOne(mappedBy = "userInfo")
    private FavoriteBag favoriteBag;

    @OneToOne(mappedBy = "userInfo")
    private ShoppingBag shoppingBag;

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
}
