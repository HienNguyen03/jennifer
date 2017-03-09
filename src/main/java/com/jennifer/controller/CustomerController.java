package com.jennifer.controller;

import com.jennifer.entity.CampaignProduct;
import com.jennifer.entity.ProductInfo;
import com.jennifer.entity.UserInfo;
import com.jennifer.service.MarketingCampaignService;
import com.jennifer.service.ProductInfoService;
import com.jennifer.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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

    @GetMapping("/checkout")
    public String checkout(){
        return "checkout";
    }

    @GetMapping("/cart")
    public String cart(){
        return "cart";
    }

    @GetMapping("/favorite")
    public String favorite(){
        return "favorite";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }


    @GetMapping("/product/{productId}")
    public String viewProduct(Model model, @PathVariable int productId){
        ProductInfo productInfo = productInfoService.findProduct(productId);
        model.addAttribute("productInfo", productInfo);

        List<ProductInfo> productInfoList = productInfoService.getSameCategoryProducts(productInfo.getCategoryInfo().getId());
        productInfoList.remove(productInfo);
        model.addAttribute("sameCategoryProducts", productInfoList);
        return "product-details";
    }

}
