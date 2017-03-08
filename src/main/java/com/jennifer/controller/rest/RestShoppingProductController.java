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

import java.util.ArrayList;
import java.util.List;

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
    public List<ShoppingProduct> createShoppingBag(){
        log.info("create shopping bag !");
        UserInfo userInfo = AppUtil.getCustomerFromSession();
        if(userInfo != null) {
            return userInfo.getShoppingProducts();
        } else {
            return new ArrayList<>();
        }
    }

    @PutMapping("/{productId}")
    public Object addProductToShoppingBag(@ModelAttribute("shoppingBag") List<ShoppingProduct> shoppingBag, @PathVariable int productId) {
        log.info(" > [rest] Shopping Product - addProductToShoppingBag");

        UserInfo userInfo = AppUtil.getCustomerFromSession();
        ProductInfo productInfo = productInfoService.findProduct(productId);

        List<ProductInfo> productsInShoppingBag = new ArrayList<>();
        for(ShoppingProduct shoppingProduct : shoppingBag){
            productsInShoppingBag.add(shoppingProduct.getProductInfo());
        }

        if(productsInShoppingBag.contains(productInfo)) {
            if(userInfo != null) {
                ShoppingProduct shoppingProduct = new ShoppingProduct(userInfo, productInfo, productInfo.getQuantity()+1);
                shoppingProductService.update(shoppingProduct);

                List<ShoppingProduct> newShoppingProduct = shoppingProductService.findAllByUserId(userInfo.getId());
                shoppingBag = newShoppingProduct;

                return newShoppingProduct.size();
            } else {

            }

        } else {
            ShoppingProduct shoppingProduct;
            if(userInfo != null) {
                shoppingProduct = new ShoppingProduct(userInfo, productInfo, 1);
                shoppingProductService.update(shoppingProduct);
            } else {
                shoppingProduct = new ShoppingProduct(null, productInfo, 1);
            }
            shoppingBag.add(shoppingProduct);
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
