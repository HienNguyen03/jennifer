package com.jennifer.controller.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jennifer.entity.ProductInfo;
import com.jennifer.service.ProductInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity update(@RequestBody ProductInfo productInfo){
        log.info(" > [rest] Product - update");

        log.info(productInfo.getName());

//        log.info(" > action: "+json.path("action").asText());
//
//        JsonNode dataNode =  json.path("data");
//        String returnObject = null;
//        for(JsonNode node : dataNode){
//            returnObject = node.toString();
//            String name = node.path("name").asText();
//            String image = null;
//            for(JsonNode imageNode : node.path("image")){
//                image += imageNode.asText() + ",";
//            }
//            String quantity = node.path("quantity").asText();
//            String description = node.path("description").asText();
//            String detail = node.path("detail").asText();
//            String tax = node.path("tax").asText();
//            String status = node.path("status").asText();
//            log.info("name: "+name);
//            log.info("image: "+image);
//            log.info("quantity: "+quantity);
//            log.info("description: "+description);
//            log.info("detail: "+detail);
//            log.info("tax: "+tax);
//            log.info("status: "+status);
//        }
//
//        ProductInfo productInfo = new ProductInfo();

        return new ResponseEntity(HttpStatus.OK);
    }

}
