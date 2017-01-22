package com.jennifer.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * For product_info table
 */
@Entity
@Table(name = "product_info")
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private CategoryInfo categoryInfo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productInfo")
    private List<ProductPrice> productPrices = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productInfo")
    private List<ProductDiscount> productDiscounts = new ArrayList<>();

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "QUANTITY", nullable = false)
    private int quatity;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DETAIL")
    private String detail;

    @Column(name = "TAX", nullable = false)
    private int tax;

    @Column(name = "STATUS", length = 45)
    private String status;

    public ProductInfo() {
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

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CategoryInfo getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(CategoryInfo categoryInfo) {
        this.categoryInfo = categoryInfo;
    }
}
