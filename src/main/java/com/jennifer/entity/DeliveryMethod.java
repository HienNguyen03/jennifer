package com.jennifer.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Handles delivery methods used in the application
 */

@Entity
@Table(name = "delivery_method")
public class DeliveryMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "COST", nullable = false)
    private BigDecimal cost;

    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE", nullable = false)
    private Date endDate;

    @JsonIgnore
    @OneToMany(mappedBy = "deliveryMethod", cascade = CascadeType.PERSIST)
    private List<OrderInfo> orderInfos = new ArrayList<>();

    public DeliveryMethod() {
    }

    public DeliveryMethod(String name, BigDecimal cost, Date startDate, Date endDate) {
        this.name = name;
        this.cost = cost;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DeliveryMethod(int id, String name, BigDecimal cost, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
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

    public List<OrderInfo> getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(List<OrderInfo> orderInfos) {
        this.orderInfos = orderInfos;
    }

}
