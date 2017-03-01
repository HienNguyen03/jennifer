package com.jennifer.controller;

import com.jennifer.service.CategoryInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controls all activities related to products' categories
 */

@Controller
public class CategoryInfoController {

    private static final Logger log = LoggerFactory.getLogger(CategoryInfoController.class);
    private CategoryInfoService categoryInfoService;

    @Autowired
    public CategoryInfoController(CategoryInfoService categoryInfoService){
        this.categoryInfoService = categoryInfoService;
    }

    @RequestMapping("/manager/category")
    public String getAllCategories(){
        return "manager/category";
    }

}
