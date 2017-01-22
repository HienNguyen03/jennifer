package com.jennifer.entity;

import javax.persistence.*;

/**
 * Handle settings of the application
 */

@Entity
@Table(name = "webstore_setting")
public class WebstoreSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "VALUE", nullable = false)
    private String value;

    public WebstoreSetting() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
