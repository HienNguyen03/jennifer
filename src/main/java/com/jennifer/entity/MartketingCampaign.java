package com.jennifer.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * For marketing_campaign table
 */
@Entity
@Table(name = "marketing_campaign")
public class MartketingCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "EVENT", length = 200)
    private String event;

    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "STATUS")
    private String status;

    public MartketingCampaign(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
