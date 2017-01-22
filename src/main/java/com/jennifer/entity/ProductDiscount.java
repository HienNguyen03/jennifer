package com.jennifer.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * For product_discount table
 */
@Entity
@Table(name = "product_discount")
public class ProductDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private ProductInfo productInfo;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "DISCOUNT", nullable = false)
    private int discount;

    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE")
    private Date endDate;

    public ProductDiscount(){}

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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }
}
