package com.jennifer.controller;

import com.jennifer.entity.*;
import com.jennifer.service.*;
import com.jennifer.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.*;

/**
 * Handles requests from Customers
 */

@Controller
@SessionAttributes({"favoriteBag", "shoppingBag"})
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private FavoriteProductService favoriteProductService;
    private ShoppingProductService shoppingProductService;
    private MarketingCampaignService marketingCampaignService;
    private ProductInfoService productInfoService;
    private ShippingAddressService shippingAddressService;
    private DeliveryMethodService deliveryMethodService;

    @Autowired
    public CustomerController(MarketingCampaignService marketingCampaignService, ProductInfoService productInfoService,
            FavoriteProductService favoriteProductService, ShoppingProductService shoppingProductService, ShippingAddressService shippingAddressService, DeliveryMethodService deliveryMethodService){
        this.marketingCampaignService = marketingCampaignService;
        this.productInfoService = productInfoService;
        this.favoriteProductService = favoriteProductService;
        this.shoppingProductService = shoppingProductService;
        this.shippingAddressService = shippingAddressService;
        this.deliveryMethodService = deliveryMethodService;
    }

    @ModelAttribute("favoriteBag")
    public List<ProductInfo> createFavoriteBag(){
        log.info("create favorite bag !");
        UserInfo userInfo = AppUtil.getCustomerFromSession();
        if(userInfo != null) {
            List<ProductInfo> productInfoList = new ArrayList<>();
            List<FavoriteProduct> favoriteProductList = favoriteProductService.findAllByUserId(userInfo.getId());
            for(FavoriteProduct favoriteProduct : favoriteProductList){
                productInfoList.add(favoriteProduct.getProductInfo());
            }
            return productInfoList;
        } else {
            return new ArrayList<>();
        }
    }

    @ModelAttribute("shoppingBag")
    public Map<ProductInfo, Integer> createShoppingBag(){
        log.info("create cart");
        UserInfo userInfo = AppUtil.getCustomerFromSession();

        if(userInfo != null && !shoppingProductService.findAllByUserId(userInfo.getId()).isEmpty()) {
            Map<ProductInfo, Integer> map = new HashMap<>();
            for (ShoppingProduct shoppingProduct : userInfo.getShoppingProducts()){
                map.put(shoppingProduct.getProductInfo(), shoppingProduct.getQuantity());
            }
            return map;
        } else {
            return new HashMap<>();
        }
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
    public String checkout(Model model){
        UserInfo userInfo = AppUtil.getCustomerFromSession();

        if(userInfo == null){
            return "redirect:/login";
        } else {
            model.addAttribute("confirmShipping", shippingAddressService.findByUser(userInfo));
            model.addAttribute("shoppingProducts", shoppingProductService.findAllByUserId(userInfo.getId()));
            model.addAttribute("deliveryMethods", deliveryMethodService.findAllAvailable());
            return "checkout";
        }

    }

    @GetMapping("/cart")
    public String cart(Model model, @ModelAttribute("shoppingBag") Map<ProductInfo, Integer> shoppingBag){
        log.info(" > CART - GET");
        UserInfo userInfo = AppUtil.getCustomerFromSession();

        if (userInfo!= null){
            List<ShoppingProduct> shoppingProducts = shoppingProductService.findAllByUserId(userInfo.getId());

            Map<ProductInfo,Integer> userShoppingBag = new HashMap<>();
            for(ShoppingProduct shoppingProduct : shoppingProducts){
               userShoppingBag.put(shoppingProduct.getProductInfo(), shoppingProduct.getQuantity());
            }

            model.addAttribute("shoppingBag", userShoppingBag);
        }
        return "cart";
    }

    @GetMapping("/favorite")
    public String favorite(Model model, @ModelAttribute("favoriteBag") List<ProductInfo> favoriteBag){
        log.info(" > Favorite Product - GET");
        UserInfo userInfo = AppUtil.getCustomerFromSession();

        if(userInfo != null){
            List<FavoriteProduct> userFavoriteBag = favoriteProductService.findAllByUserId(userInfo.getId());

            List<ProductInfo> productInfos = new ArrayList<>();
            for (FavoriteProduct f : userFavoriteBag){
                productInfos.add(f.getProductInfo());
            }
            log.info(" >Favorite Product - user");

            model.addAttribute("favoriteBag",productInfos);

        }
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
