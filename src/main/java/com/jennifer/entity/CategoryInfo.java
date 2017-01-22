package com.jennifer.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * For category_info table
 */
@Entity
@Table(name = "category_info")
public class CategoryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryInfo")
    private List<ProductInfo> productInfos = new ArrayList<>();

    @Column(name = "NAME", nullable = false, length = 60)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private CategoryInfo superCategoryInfo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryInfo")
    private List<CategoryInfo> categoryInfos = new ArrayList<>();

    public CategoryInfo() {
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
