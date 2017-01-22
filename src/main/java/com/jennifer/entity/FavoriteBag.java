package com.jennifer.entity;

import javax.persistence.*;

/**
 * Handles favorite items of each customer
 */

@Entity
@Table(name = "favorite_bag")
public class FavoriteBag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private UserInfo userInfo;

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
}
