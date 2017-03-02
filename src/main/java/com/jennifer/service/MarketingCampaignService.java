package com.jennifer.service;

import com.jennifer.entity.MarketingCampaign;
import com.jennifer.entity.MarketingCampaign;

import java.util.List;

/**
 * Provided services for MarketingCampaign
 */

public interface MarketingCampaignService {

    List<MarketingCampaign> findAllCampaigns();
    MarketingCampaign findCampaign(int id);
    MarketingCampaign updateCampaign(MarketingCampaign campaign);
    void deleteCampaign(MarketingCampaign campaign);
    MarketingCampaign addCampaign(MarketingCampaign campaign);

}
