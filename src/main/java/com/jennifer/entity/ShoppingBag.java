package com.jennifer.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles shopping bags of each customer
 */

@Entity
@Table(name = "shopping_bag")
public class ShoppingBag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private UserInfo userInfo;

    @OneToMany(mappedBy = "primaryKey.shoppingBag", cascade = CascadeType.ALL)
    private List<ShoppingProduct> shoppingProducts = new ArrayList<>();

    public ShoppingBag() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<ShoppingProduct> getShoppingProducts() {
        return shoppingProducts;
    }

    public void setShoppingProducts(List<ShoppingProduct> shoppingProducts) {
        this.shoppingProducts = shoppingProducts;
    }
}
