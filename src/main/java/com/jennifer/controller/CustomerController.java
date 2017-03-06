package com.jennifer.controller;

import com.jennifer.entity.ProductInfo;
import com.jennifer.service.MarketingCampaignService;
import com.jennifer.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Handles requests from Customers
 */

@Controller
public class CustomerController {

    private MarketingCampaignService marketingCampaignService;
    private ProductInfoService productInfoService;

    @Autowired
    public CustomerController(MarketingCampaignService marketingCampaignService, ProductInfoService productInfoService){
        this.marketingCampaignService = marketingCampaignService;
        this.productInfoService = productInfoService;
    }

    @GetMapping("/")
    public String customer_homepage(Model model){
        model.addAttribute("availableCampaigns", marketingCampaignService.getAvailableCampaigns());
        model.addAttribute("availableProducts", productInfoService.getLatestProducts());
        return "index";
    }

    @GetMapping("/shop")
    public String shop(){
        return "shop";
    }

    @GetMapping("/product-details")
    public String productDetails(){
        return "product-details";
    }

    @GetMapping("/checkout")
    public String checkout(){
        return "checkout";
    }

    @GetMapping("/cart")
    public String cart(){
        return "cart";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }


    @GetMapping("/product/{productId}")
    public String viewProduct(Model model, @PathVariable int productId){
        model.addAttribute("productInfo", productInfoService.findProduct(productId));
        return "product-details";
    }

}
