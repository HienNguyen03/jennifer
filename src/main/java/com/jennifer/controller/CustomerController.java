package com.jennifer.controller;

import com.jennifer.controller.rest.RestFavoriteProductController;
import com.jennifer.entity.FavoriteProduct;
import com.jennifer.entity.ProductInfo;
import com.jennifer.entity.UserInfo;
import com.jennifer.service.FavoriteProductService;
import com.jennifer.service.MarketingCampaignService;
import com.jennifer.service.ProductInfoService;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Handles requests from Customers
 */

@Controller
@SessionAttributes({"favoriteBag"})
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private FavoriteProductService favoriteProductService;
    private MarketingCampaignService marketingCampaignService;
    private ProductInfoService productInfoService;

    @Autowired
    public CustomerController(MarketingCampaignService marketingCampaignService, ProductInfoService productInfoService,
            FavoriteProductService favoriteProductService){
        this.marketingCampaignService = marketingCampaignService;
        this.productInfoService = productInfoService;
        this.favoriteProductService = favoriteProductService;
    }

    @ModelAttribute("favoriteBag")
    public List<ProductInfo> createFavoriteBag(){
        log.info("create favorite bag !");
        return RestFavoriteProductController.createFavoriteBag();
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

        }else{
            log.info(""+favoriteBag.size());
            model.addAttribute("favoriteBag",favoriteBag);

        }
        return "favorite";
    }

//    @GetMapping("/favorite/delete/{productId}")
//    public String favoriteDelete(Model model, @ModelAttribute("favoriteBag") List<ProductInfo> favoriteBag
//            , @PathVariable("productId") int productId){
//        log.info(" > Favorite Product - DELETE");
//        UserInfo userInfo = AppUtil.getCustomerFromSession();
//
//        if(userInfo != null){
//            FavoriteProduct delFavoriteProduct = favoriteProductService.findByUserIdAndProductId(userInfo.getId(), productId);
//            favoriteProductService.delete(delFavoriteProduct);
//
//            List<FavoriteProduct> userFavoriteBag = favoriteProductService.findAllByUserId(userInfo.getId());
//            List<ProductInfo> productInfos = new ArrayList<>();
//            for (FavoriteProduct f : userFavoriteBag){
//                productInfos.add(f.getProductInfo());
//            }
//            log.info(" >Favorite Product - user");
//            model.addAttribute("favoriteBag",productInfos);
//
//        }else{
//            log.info(""+favoriteBag.size());
//            for(ProductInfo p: favoriteBag){
//                if(p.getId()== productId){
//                    favoriteBag.remove(p);
//                }
//            }
//            log.info(""+favoriteBag.size());
//            model.addAttribute("favoriteBag",favoriteBag);
//
//        }
//        return "favorite";
//    }

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
