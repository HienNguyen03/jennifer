package com.jennifer.controller;

import com.jennifer.entity.CampaignProduct;
import com.jennifer.entity.MarketingCampaign;
import com.jennifer.entity.ProductInfo;
import com.jennifer.service.CampaignProductService;
import com.jennifer.service.MarketingCampaignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Controls all activities related to marketing campaigns
 */

@Controller
public class MarketingCampaignController {

    private static final Logger log = LoggerFactory.getLogger(ProductInfoController.class);
    private MarketingCampaignService marketingCampaignService;
    private CampaignProductService campaignProductService;

    @Autowired
    public MarketingCampaignController(MarketingCampaignService marketingCampaignService,
                                       CampaignProductService campaignProductService){
        this.marketingCampaignService = marketingCampaignService;
        this.campaignProductService = campaignProductService;
    }

    @RequestMapping("/manager/marketing")
    public String getAllCampaigns(){
        return "manager/marketing";
    }

    @GetMapping("/campaign/{campaignId}")
    public String getProductCampain(Model model, @PathVariable("campaignId") int campaignId){

        MarketingCampaign marketingCampaign = marketingCampaignService.findCampaign(campaignId);
        List<CampaignProduct> campaignProducts = campaignProductService.findAllByCampaignId(campaignId);
        List<ProductInfo> productInfos = new ArrayList<>();
        for (CampaignProduct c: campaignProducts){
            productInfos.add(c.getProductInfo());
        }

        model.addAttribute("productCampaign", productInfos);
        model.addAttribute("availableCampaigns", marketingCampaignService.getAvailableCampaigns());
        model.addAttribute("campaign", marketingCampaign);
        return "campaign";
    }

}
