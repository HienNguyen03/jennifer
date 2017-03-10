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
@SessionAttributes({"shoppingBag", "shoppingBagAnchor"})
public class RestShoppingProductController {

    private static final Logger log = LoggerFactory.getLogger(RestShoppingProductController.class);
    private ProductInfoService productInfoService;
    private ShoppingProductService shoppingProductService;

    @Autowired
    public RestShoppingProductController(ProductInfoService productInfoService, ShoppingProductService shoppingProductService){
        this.productInfoService = productInfoService;
        this.shoppingProductService = shoppingProductService;
    }

    @ModelAttribute("shoppingBagAnchor")
    public ShoppingProductService createShoppingBagAnchor(){
        return shoppingProductService;
    }

    @ModelAttribute("shoppingBag")
    public static Map<ProductInfo, Integer> createShoppingBag(){
        log.info("create cart");
        UserInfo userInfo = AppUtil.getCustomerFromSession();
        if(userInfo != null && !userInfo.getShoppingProducts().isEmpty()) {
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

        if(userInfo != null){
            ProductInfo productInfo = productInfoService.findProduct(productId);
            ShoppingProduct shoppingProduct = new ShoppingProduct(userInfo, productInfo, shoppingBag.get(productInfo));
            shoppingBag.remove(productInfo);
            shoppingProductService.delete(shoppingProduct);

            return shoppingBag.size();
        }else{
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

//    @GetMapping
//    public Object findAll() throws JsonProcessingException {
//        log.info(" > [rest] Product - findAll");
//        List<ProductInfo> productInfoList = productInfoService.findAllProducts();
//        if (productInfoList.isEmpty()) {
//            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
//        }
//
//        ObjectMapper mapper = new ObjectMapper();
//        SimpleModule module = new SimpleModule();
//        module.addSerializer(List.class, new ProductInfoSerializer());
//        mapper.registerModule(module);
//
//        return mapper.writeValueAsString(productInfoList);
//    }
//
//    @PutMapping
//    public Object update(@RequestBody ProductInfo productInfo){
//        log.info(" > [rest] Product - update");
//        ProductInfo productInfoData = productInfoService.findProduct(productInfo.getId());
//
//        if(productInfoData != null)
//            return productInfoService.updateProduct(productInfo);
//
//        return new ResponseEntity<>("Unable to delete!", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @DeleteMapping
//    public Object delete(@RequestBody ProductInfo productInfo){
//        log.info(" > [rest] Product - delete");
//        ProductInfo productInfoData = productInfoService.findProduct(productInfo.getId());
//
//        try {
//            if(productInfoData != null)
//                productInfoService.deleteProduct(productInfo);
//            return productInfoData;
//        } catch (DataIntegrityViolationException e){
//            return new ResponseEntity<>("Product '" + productInfoData.getName() + "' is in used! Unable to delete!", HttpStatus.CONFLICT);
//        } catch (Exception e){
//            return new ResponseEntity<>("Unable to delete!", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PostMapping
//    public ProductInfo insert(@RequestBody ProductInfo productInfo){
//        log.info(" > [rest] Product - insert");
//        return productInfoService.addProduct(productInfo);
//    }

}
