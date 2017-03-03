package com.jennifer.dao;

import com.jennifer.entity.CampaignProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * CampaignProduct data access object interface
 */

public interface CampaignProductDao extends JpaRepository<CampaignProduct, Integer> {

    List<CampaignProduct> findAll();

    @Query("from CampaignProduct cp where cp.primaryKey.marketingCampaign.id = ?1 order by cp.primaryKey.productInfo.id desc")
    List<CampaignProduct> findAllByCampaignId(int campaignId);

    @Query("from CampaignProduct cp where cp.primaryKey.marketingCampaign.id = ?1 and cp.primaryKey.productInfo.id = ?2")
    CampaignProduct findByCampaignIdAndProductId(int campaignId, int productId);

}
