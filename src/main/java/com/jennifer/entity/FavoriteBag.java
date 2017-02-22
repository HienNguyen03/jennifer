package com.jennifer.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles favorite items of each customer
 */

@Entity
@Table(name = "favorite_bag")
public class FavoriteBag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private UserInfo userInfo;

    @ManyToMany(mappedBy = "favoriteBags", cascade = CascadeType.ALL)
    private List<ProductInfo> productInfos = new ArrayList<>();

    public FavoriteBag() {
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

    public List<ProductInfo> getProductInfos() {
        return productInfos;
    }

    public void setProductInfos(List<ProductInfo> productInfos) {
        this.productInfos = productInfos;
    }
}
