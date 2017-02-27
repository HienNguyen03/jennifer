package com.jennifer.controller.rest;

import com.jennifer.entity.ProductInfo;
import com.jennifer.service.ProductInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest API Controller for ProductInfo
 */

@RestController
@RequestMapping("/api/product")
public class RestProductInfoController {

    private static final Logger log = LoggerFactory.getLogger(RestProductInfoController.class);
    private ProductInfoService productInfoService;

    @Autowired
    public RestProductInfoController(ProductInfoService productInfoService){
        this.productInfoService = productInfoService;
    }

    @GetMapping
    public List<ProductInfo> findAll(){
        log.info(" > [rest] Product - findAll");
        return productInfoService.findAllProducts();
    }

    @PutMapping
    public ProductInfo update(@RequestBody ProductInfo productInfo){
        log.info(" > [rest] Product - update");
        ProductInfo productInfoData = productInfoService.findProduct(productInfo.getId());
        if(productInfoData != null)
            return productInfoService.updateProduct(productInfo);
        return null;
    }

    @DeleteMapping
    public ProductInfo delete(@RequestBody ProductInfo productInfo){
        log.info(" > [rest] Product - delete");
        ProductInfo productInfoData = productInfoService.findProduct(productInfo.getId());
        if(productInfoData != null)
            productInfoService.deleteProduct(productInfo);
        return productInfoData;
    }

    @PostMapping
    public ProductInfo insert(@RequestBody ProductInfo productInfo){
        log.info(" > [rest] Product - insert");
        return productInfoService.addProduct(productInfo);
    }

}
