package com.jennifer.controller;

import com.jennifer.service.ProductInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controls all activities related to products
 */

@Controller
public class ProductInfoController {

    private static final Logger log = LoggerFactory.getLogger(ProductInfoController.class);
    private ProductInfoService productInfoService;

    @Autowired
    public ProductInfoController(ProductInfoService productInfoService){
        this.productInfoService = productInfoService;
    }

    @RequestMapping("/manager/product")
    public String mListOfProducts(Model model){
       model.addAttribute("products", productInfoService.findAllProducts());
        return "manager/product";
    }

}
