package com.jennifer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @JsonIgnore
    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    @JsonIgnore
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
    @OneToMany(mappedBy = "primaryKey.userInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<ShoppingProduct> shoppingProducts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "primaryKey.userInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<ViewedProduct> viewedProducts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "primaryKey.userInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<FavoriteProduct> favoriteProducts = new ArrayList<>();

    public UserInfo() {
    }

    public UserInfo(String fullname, String email, String password, Role role, List<ProductInfo> productInfos) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public List<FavoriteProduct> getFavoriteProducts() {
        return favoriteProducts;
    }

    public void setFavoriteProducts(List<FavoriteProduct> favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
    }

    public List<ShoppingProduct> getShoppingProducts() {
        return shoppingProducts;
    }

    public void setShoppingProducts(List<ShoppingProduct> shoppingProducts) {
        this.shoppingProducts = shoppingProducts;
    }

    public List<ViewedProduct> getViewedProducts() {
        return viewedProducts;
    }

    public void setViewedProducts(List<ViewedProduct> viewedProducts) {
        this.viewedProducts = viewedProducts;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo)) return false;

        UserInfo userInfo = (UserInfo) o;

        if (getId() != userInfo.getId()) return false;
        if (getFullname() != null ? !getFullname().equals(userInfo.getFullname()) : userInfo.getFullname() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(userInfo.getEmail()) : userInfo.getEmail() != null) return false;
        if (getPassword() != null ? !getPassword().equals(userInfo.getPassword()) : userInfo.getPassword() != null)
            return false;
        return getRole() == userInfo.getRole();
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getFullname() != null ? getFullname().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        return result;
    }
}
