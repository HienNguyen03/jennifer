package com.jennifer.entity;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jennifer.controller.rest.deserializer.ProductInfoDeserializer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Handles selling products' information
 */
@Entity
@Table(name = "product_info")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonDeserialize(using = ProductInfoDeserializer.class)
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "UNIT_PRICE", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "DISCOUNT")
    private int discount;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DETAIL")
    private String detail;

    @Column(name = "STATUS", length = 45)
    private String status;

    //@JsonManagedReference("product-category")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID")
    private CategoryInfo categoryInfo;

    @JsonIgnore
    @OneToMany(mappedBy = "primaryKey.productInfo", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "primaryKey.productInfo", cascade = CascadeType.ALL)
    private List<CampaignProduct> campaignProducts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "primaryKey.productInfo")
    private Set<ShoppingProduct> shoppingProducts = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "primaryKey.productInfo")
    private Set<ViewedProduct> viewedProducts = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "primaryKey.productInfo")
    private Set<FavoriteProduct> favoriteProducts = new LinkedHashSet<>();

    public ProductInfo() {
    }

    public ProductInfo(String name, BigDecimal unitPrice, int discount, String image, int quantity, String description, String detail, String status, CategoryInfo categoryInfo) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.image = image;
        this.quantity = quantity;
        this.description = description;
        this.detail = detail;
        this.status = status;
        this.categoryInfo = categoryInfo;
    }

    public ProductInfo(int id, String name, BigDecimal unitPrice, int discount, String image, int quantity, String description, String detail, String status, CategoryInfo categoryInfo) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.image = image;
        this.quantity = quantity;
        this.description = description;
        this.detail = detail;
        this.status = status;
        this.categoryInfo = categoryInfo;
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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<CampaignProduct> getCampaignProducts() {
        return campaignProducts;
    }

    public void setCampaignProducts(List<CampaignProduct> campaignProducts) {
        this.campaignProducts = campaignProducts;
    }

    public Set<ShoppingProduct> getShoppingProducts() {
        return shoppingProducts;
    }

    public void setShoppingProducts(Set<ShoppingProduct> shoppingProducts) {
        this.shoppingProducts = shoppingProducts;
    }

    public Set<ViewedProduct> getViewedProducts() {
        return viewedProducts;
    }

    public void setViewedProducts(Set<ViewedProduct> viewedProducts) {
        this.viewedProducts = viewedProducts;
    }

    public Set<FavoriteProduct> getFavoriteProducts() {
        return favoriteProducts;
    }

    public void setFavoriteProducts(Set<FavoriteProduct> favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", discount=" + discount +
                ", image='" + image + '\'' +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", detail='" + detail + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductInfo)) return false;

        ProductInfo that = (ProductInfo) o;

        if (getId() != that.getId()) return false;
        if (getQuantity() != that.getQuantity()) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getUnitPrice() != null ? !getUnitPrice().equals(that.getUnitPrice()) : that.getUnitPrice() != null)
            return false;
        return getStatus() != null ? getStatus().equals(that.getStatus()) : that.getStatus() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getUnitPrice() != null ? getUnitPrice().hashCode() : 0);
        result = 31 * result + getQuantity();
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }
}
