package com.jennifer.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles products' categories in the webstore
 */
@Entity
@Table(name = "category_info")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CategoryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", nullable = false, length = 60)
    private String name;

    @JsonBackReference("super-category")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID")
    private CategoryInfo superCategoryInfo;

    @Column(name = "PLACE_ORDER", nullable = false)
    private int placeOrder;

    //@JsonBackReference("product-category")
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryInfo")
    private List<ProductInfo> productInfos = new ArrayList<>();

    @JsonManagedReference("super-category")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "superCategoryInfo")
    private List<CategoryInfo> categoryInfos = new ArrayList<>();

    public CategoryInfo() {
    }

    public CategoryInfo(String name, CategoryInfo superCategoryInfo, int placeOrder) {
        this.name = name;
        this.superCategoryInfo = superCategoryInfo;
        this.placeOrder = placeOrder;
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

    public List<ProductInfo> getProductInfos() {
        return productInfos;
    }

    public void setProductInfos(List<ProductInfo> productInfos) {
        this.productInfos = productInfos;
    }

    public int getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(int placeOrder) {
        this.placeOrder = placeOrder;
    }

    public CategoryInfo getSuperCategoryInfo() {
        return superCategoryInfo;
    }

    public void setSuperCategoryInfo(CategoryInfo superCategoryInfo) {
        this.superCategoryInfo = superCategoryInfo;
    }

    public List<CategoryInfo> getCategoryInfos() {
        return categoryInfos;
    }

    public void setCategoryInfos(List<CategoryInfo> categoryInfos) {
        this.categoryInfos = categoryInfos;
    }

}
