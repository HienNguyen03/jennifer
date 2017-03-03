package com.jennifer.service;

import com.jennifer.entity.CampaignProduct;

import java.util.List;

/**
 * Provided services for CampaignProduct
 */

public interface CampaignProductService {

    List<CampaignProduct> findAllCampaignProducts();
    CampaignProduct updateCampaignProduct(CampaignProduct product);
    void deleteCampaignProduct(CampaignProduct product);
    CampaignProduct addCampaignProduct(CampaignProduct product);
    List<CampaignProduct> findAllByCampaignId(int campaignId);
    CampaignProduct findByCampaignIdAndProductId(int campaignId, int productId);

}
