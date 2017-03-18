package com.jennifer.controller.rest;

import com.jennifer.entity.ProductInfo;
import com.jennifer.entity.ShoppingProduct;
import com.jennifer.entity.UserInfo;
import com.jennifer.service.ProductInfoService;
import com.jennifer.service.ShoppingProductService;
import com.jennifer.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Rest API Controller for ShoppingProduct
 */

@RestController
@RequestMapping("/api/shopping-product")
@SessionAttributes({"shoppingBag"})
public class RestShoppingProductController {

    private static final Logger log = LoggerFactory.getLogger(RestShoppingProductController.class);
    private ProductInfoService productInfoService;
    private ShoppingProductService shoppingProductService;

    @Autowired
    public RestShoppingProductController(ProductInfoService productInfoService, ShoppingProductService shoppingProductService){
        this.productInfoService = productInfoService;
        this.shoppingProductService = shoppingProductService;
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

    @PutMapping("/{productId}")
    public Object addProductToShoppingBag(@ModelAttribute("shoppingBag") Map<ProductInfo, Integer> shoppingBag, @PathVariable int productId) {
        log.info(" > [rest] Shopping Product - addProductToShoppingBag");

        UserInfo userInfo = AppUtil.getCustomerFromSession();
        ProductInfo productInfo = productInfoService.findProduct(productId);

        ShoppingProduct shoppingProduct;

        if(shoppingBag.containsKey(productInfo)) {
            shoppingBag.put(productInfo, shoppingBag.get(productInfo) + 1);
            if(userInfo != null) {
                shoppingProduct = new ShoppingProduct(userInfo, productInfo, shoppingBag.get(productInfo));
                shoppingProductService.update(shoppingProduct);
            }
            return shoppingBag.size();
        } else {
            shoppingBag.put(productInfo, 1);
            if(userInfo != null) {
                shoppingProduct = new ShoppingProduct(userInfo, productInfo, 1);
                shoppingProductService.update(shoppingProduct);
            }
            return shoppingBag.size();
        }

    }

    @DeleteMapping("/{productId}")
    public Object deleteProductInShoppingBag(@ModelAttribute("shoppingBag") Map<ProductInfo, Integer> shoppingBag, @PathVariable int productId ){
        UserInfo userInfo = AppUtil.getCustomerFromSession();

        if(userInfo != null) {
            ProductInfo productInfo = productInfoService.findProduct(productId);

            ShoppingProduct shoppingProduct = new ShoppingProduct(userInfo, productInfo, shoppingBag.get(productInfo));
            shoppingProductService.delete(shoppingProduct);

            shoppingBag.remove(productInfo);

            return shoppingBag.size();
        } else {
            ProductInfo productInfo = productInfoService.findProduct(productId);
            shoppingBag.remove(productInfo);
            return shoppingBag.size();
        }
    }



    @PutMapping("/a/{productId}/{chooseQuantity}")
    public Object addProductToShoppingBagAlone(@ModelAttribute("shoppingBag") Map<ProductInfo, Integer> shoppingBag, @PathVariable int productId, @PathVariable int chooseQuantity) {
        log.info(" > [rest] Shopping Product - addProductToShoppingBagAlone");

        UserInfo userInfo = AppUtil.getCustomerFromSession();
        ProductInfo productInfo = productInfoService.findProduct(productId);

        ShoppingProduct shoppingProduct;

        if(shoppingBag.containsKey(productInfo)) {
            shoppingBag.put(productInfo, shoppingBag.get(productInfo) + chooseQuantity);
            if(userInfo != null) {
                shoppingProduct = new ShoppingProduct(userInfo, productInfo, shoppingBag.get(productInfo)+chooseQuantity);
                shoppingProductService.update(shoppingProduct);
            }
            return shoppingBag.size();
        } else {
            shoppingBag.put(productInfo, chooseQuantity);
            if(userInfo != null) {
                shoppingProduct = new ShoppingProduct(userInfo, productInfo, chooseQuantity);
                shoppingProductService.update(shoppingProduct);
            }
            return shoppingBag.size();
        }

    }

    @PutMapping("/q/{productId}/{updateQuantity}")
    public Object addProductToShoppingBagQuantity(@ModelAttribute("shoppingBag") Map<ProductInfo, Integer> shoppingBag, @PathVariable int productId, @PathVariable int updateQuantity) {
        log.info(" > [rest] Shopping Product - addProductToShoppingBagQuantity");

        UserInfo userInfo = AppUtil.getCustomerFromSession();
        ProductInfo productInfo = productInfoService.findProduct(productId);

        shoppingBag.put(productInfo, updateQuantity);
        if(userInfo != null) {
            ShoppingProduct shoppingProduct = new ShoppingProduct(userInfo, productInfo, updateQuantity);
            shoppingProductService.update(shoppingProduct);
        }
        return shoppingBag.size();

    }
}
