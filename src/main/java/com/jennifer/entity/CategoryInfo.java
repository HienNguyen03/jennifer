package com.jennifer.entity;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jennifer.controller.rest.deserializer.CategoryInfoDeserializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles products' categories in the webstore
 */
@Entity
@Table(name = "category_info")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonDeserialize(using = CategoryInfoDeserializer.class)
public class CategoryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", nullable = false, length = 60)
    private String name;

    @JsonManagedReference("super-category")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID")
    private CategoryInfo superCategoryInfo;

    @Column(name = "PLACE_ORDER", nullable = false)
    private int placeOrder;

    //@JsonBackReference("product-category")
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryInfo")
    private List<ProductInfo> productInfos = new ArrayList<>();

    @JsonBackReference("super-category")
    //@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "superCategoryInfo")
    private List<CategoryInfo> categoryInfos = new ArrayList<>();

    public CategoryInfo() {
    }

    public CategoryInfo(int id, String name, CategoryInfo superCategoryInfo, int placeOrder) {
        this.id = id;
        this.name = name;
        this.superCategoryInfo = superCategoryInfo;
        this.placeOrder = placeOrder;
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

    @Override
    public String toString() {
        return "CategoryInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", superCategoryInfo=" + superCategoryInfo +
                ", placeOrder=" + placeOrder +
                '}';
    }
}
