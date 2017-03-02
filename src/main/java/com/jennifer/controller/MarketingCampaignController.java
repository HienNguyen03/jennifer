package com.jennifer.controller;

import com.jennifer.service.MarketingCampaignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controls all activities related to marketing campaigns
 */

@Controller
public class MarketingCampaignController {

    private static final Logger log = LoggerFactory.getLogger(ProductInfoController.class);
    private MarketingCampaignService marketingCampaignService;

    @Autowired
    public MarketingCampaignController(MarketingCampaignService marketingCampaignService){
        this.marketingCampaignService = marketingCampaignService;
    }

    @RequestMapping("/manager/marketing")
    public String getAllCampaigns(){
        return "manager/marketing";
    }

}
