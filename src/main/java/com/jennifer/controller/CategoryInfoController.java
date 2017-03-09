package com.jennifer.controller;

import com.jennifer.entity.CategoryInfo;
import com.jennifer.entity.ProductInfo;
import com.jennifer.service.CategoryInfoService;
import com.jennifer.service.MarketingCampaignService;
import com.jennifer.service.ProductInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Controls all activities related to products' categories
 */

@Controller
public class CategoryInfoController {

    private static final Logger log = LoggerFactory.getLogger(CategoryInfoController.class);
    private CategoryInfoService categoryInfoService;
    private ProductInfoService productInfoService;
    private MarketingCampaignService marketingCampaignService;

    @Autowired
    public CategoryInfoController(CategoryInfoService categoryInfoService, ProductInfoService productInfoService, MarketingCampaignService marketingCampaignService){
        this.categoryInfoService = categoryInfoService;
        this.productInfoService = productInfoService;
        this.marketingCampaignService = marketingCampaignService;
    }

    @RequestMapping("/manager/category")
    public String getAllCategories(){
        return "manager/category";
    }


    @RequestMapping(value = "/categories" , method = RequestMethod.GET)
    public String getCAtegoryFragment(Model model){
        List<CategoryInfo> categoryInfos = categoryInfoService.findAllCategories();
//        for (CategoryInfo c: categoryInfos){
//            log.info(c.toString());
//        }
        model.addAttribute("categoryInfos",categoryInfos);
        return "common/sidebar :: common-sidebar-customer";
    }

    @RequestMapping(value = "/categories/{categoryId}")
    public String categoryProducts(Model model, @PathVariable("categoryId") int categoryId){
        log.info("categoryID " + categoryId);

        CategoryInfo categoryInfo = categoryInfoService.findCategory(categoryId);
        List<ProductInfo> productInfos = productInfoService.findProductsByCategory(categoryInfo);

        model.addAttribute("categoryInfo", categoryInfo);
        model.addAttribute("productInfos", productInfos);
        model.addAttribute("availableCampaigns", marketingCampaignService.getAvailableCampaigns());

        return "category-products";

    }

}
