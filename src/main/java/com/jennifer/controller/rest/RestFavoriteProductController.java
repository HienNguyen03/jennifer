package com.jennifer.controller.rest;

import com.jennifer.entity.FavoriteProduct;
import com.jennifer.entity.ProductInfo;
import com.jennifer.entity.UserInfo;
import com.jennifer.service.FavoriteProductService;
import com.jennifer.service.ProductInfoService;
import com.jennifer.service.UserInfoService;
import com.jennifer.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Rest API Controller for ProductInfo
 */

@RestController
@RequestMapping("/api/favorite-product")
@SessionAttributes({"favoriteBag"})
public class RestFavoriteProductController {

    private static final Logger log = LoggerFactory.getLogger(RestFavoriteProductController.class);
    private ProductInfoService productInfoService;
    private FavoriteProductService favoriteProductService;

    @Autowired
    public RestFavoriteProductController(ProductInfoService productInfoService, FavoriteProductService favoriteProductService){
        this.productInfoService = productInfoService;
        this.favoriteProductService = favoriteProductService;
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

    @PutMapping("/{productId}")
    public Object addProductToFavorite(@ModelAttribute("favoriteBag") List<ProductInfo> favoriteBag, @PathVariable int productId) {
        log.info(" > [rest] Favorite Product - addProductToFavorite");

        UserInfo userInfo = AppUtil.getCustomerFromSession();
        ProductInfo productInfo = productInfoService.findProduct(productId);

        if(favoriteBag.contains(productInfo)){
            return new ResponseEntity<>("The product was added to your favorite!", HttpStatus.OK);
        } else {
            if(userInfo != null) {
                // add to favorite bag - database
                FavoriteProduct favoriteProduct = new FavoriteProduct(userInfo, productInfo);
                favoriteProductService.update(favoriteProduct);

                // add to favorite bag - session
                favoriteBag.add(productInfo);
                return favoriteBag.size();
            } else {
                favoriteBag.add(productInfo);
                return favoriteBag.size();
            }
        }
    }

    @DeleteMapping("/{productId}")
    public Object deleteProductToFavorite(@ModelAttribute("favoriteBag") List<ProductInfo> favoriteBag, @PathVariable int productId) {
        log.info(" > [rest] Favorite Product - deleteProductToFavorite");
        UserInfo userInfo = AppUtil.getCustomerFromSession();

        if(userInfo != null) {
            ProductInfo productInfo = productInfoService.findProduct(productId);
            favoriteBag.remove(productInfo);

            FavoriteProduct favoriteProduct = new FavoriteProduct(userInfo, productInfo);
            favoriteProductService.delete(favoriteProduct);

            return favoriteBag.size();
        } else {
            for (Iterator<ProductInfo> iter = favoriteBag.listIterator(); iter.hasNext(); ) {
                ProductInfo productInfo = iter.next();
                if (productInfo.getId() == productId) {
                    iter.remove();
                }
            }

            return favoriteBag.size();
        }
    }

}
