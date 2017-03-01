package com.jennifer.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jennifer.controller.rest.serializer.ProductInfoSerializer;
import com.jennifer.entity.ProductInfo;
import com.jennifer.service.ProductInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public Object findAll() throws JsonProcessingException {
        log.info(" > [rest] Product - findAll");
        List<ProductInfo> productInfoList = productInfoService.findAllProducts();
        if (productInfoList.isEmpty()) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(List.class, new ProductInfoSerializer());
        mapper.registerModule(module);

        return mapper.writeValueAsString(productInfoList);
    }

    @PutMapping
    public Object update(@RequestBody ProductInfo productInfo){
        log.info(" > [rest] Product - update");
        ProductInfo productInfoData = productInfoService.findProduct(productInfo.getId());

        if(productInfoData != null)
            return productInfoService.updateProduct(productInfo);

        return new ResponseEntity("Unable to delete!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping
    public Object delete(@RequestBody ProductInfo productInfo){
        log.info(" > [rest] Product - delete");
        ProductInfo productInfoData = productInfoService.findProduct(productInfo.getId());

        try {
            if(productInfoData != null)
                productInfoService.deleteProduct(productInfo);
            return productInfoData;
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity("Product '" + productInfoData.getName() + "' is in used! Unable to delete!", HttpStatus.CONFLICT);
        } catch (Exception e){
            return new ResponseEntity("Unable to delete!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ProductInfo insert(@RequestBody ProductInfo productInfo){
        log.info(" > [rest] Product - insert");
        return productInfoService.addProduct(productInfo);
    }

}
