package com.jennifer.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jennifer.controller.rest.deserializer.CampaignProductDeserializer;

import javax.persistence.*;

/**
 * Handles detailed products inside each marketing campaign.
 */

@Entity
@Table(name = "campaign_product")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.productInfo", joinColumns = @JoinColumn(name = "PRODUCT_ID")),
        @AssociationOverride(name = "primaryKey.marketingCampaign", joinColumns = @JoinColumn(name = "CAMPAIGN_ID"))
})
@JsonDeserialize(using = CampaignProductDeserializer.class)
public class CampaignProduct {

    // composite id key
    @EmbeddedId
    private CampaignProductId primaryKey = new CampaignProductId();

    @Column(name = "DISCOUNT", nullable = false)
    private int discount;

    public CampaignProduct() {
    }

    public CampaignProduct(MarketingCampaign marketingCampaign, ProductInfo productInfo, int discount) {
        this.primaryKey.setProductInfo(productInfo);
        this.primaryKey.setMarketingCampaign(marketingCampaign);
        this.discount = discount;
    }

    private CampaignProductId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(CampaignProductId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public ProductInfo getProductInfo(){
        return getPrimaryKey().getProductInfo();
    }

    public void setProductInfo(ProductInfo productInfo){
        getPrimaryKey().setProductInfo(productInfo);
    }

    @Transient
    public MarketingCampaign getMarketingCampaign(){
        return getPrimaryKey().getMarketingCampaign();
    }

    public void setMarketingCampaign(MarketingCampaign marketingCampaign){
        getPrimaryKey().setMarketingCampaign(marketingCampaign);
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
