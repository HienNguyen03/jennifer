package com.jennifer.entity;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Composite Id class for the composite key
 */

@Embeddable
class CampaignProductId implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    private ProductInfo productInfo;

    @ManyToOne(cascade = CascadeType.ALL)
    private MarketingCampaign marketingCampaign;

    ProductInfo getProductInfo() {
        return productInfo;
    }

    void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    MarketingCampaign getMarketingCampaign() {
        return marketingCampaign;
    }

    void setMarketingCampaign(MarketingCampaign marketingCampaign) {
        this.marketingCampaign = marketingCampaign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CampaignProductId)) return false;

        CampaignProductId that = (CampaignProductId) o;

        if (!getProductInfo().equals(that.getProductInfo())) return false;
        return getMarketingCampaign().equals(that.getMarketingCampaign());
    }

    @Override
    public int hashCode() {
        int result = getProductInfo().hashCode();
        result = 31 * result + getMarketingCampaign().hashCode();
        return result;
    }
}
