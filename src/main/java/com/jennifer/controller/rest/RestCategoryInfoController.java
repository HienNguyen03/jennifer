package com.jennifer.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jennifer.controller.rest.serializer.CategoryInfoSerializer;
import com.jennifer.entity.CategoryInfo;
import com.jennifer.service.CategoryInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Rest API Controller for CategoryInfo
 */

@RestController
@RequestMapping("/api/category")
public class RestCategoryInfoController {

    private static final Logger log = LoggerFactory.getLogger(RestCategoryInfoController.class);
    private CategoryInfoService categoryInfoService;

    @Autowired
    public RestCategoryInfoController(CategoryInfoService categoryInfoService) {
        this.categoryInfoService = categoryInfoService;
    }

    @GetMapping
    public Object findAll() throws JsonProcessingException {
        List<CategoryInfo> categoryInfoList = categoryInfoService.findAllCategories();
        if (categoryInfoList.isEmpty()) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Map.class, new CategoryInfoSerializer());
        //mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
        mapper.registerModule(module);

        Map<CategoryInfo, List<CategoryInfo>> map = new HashMap<>();
        for (CategoryInfo category : categoryInfoList) {
            CategoryInfo parentCategory = category.getSuperCategoryInfo();
            if (category.getSuperCategoryInfo() == null) {
                map.put(category, new ArrayList<>());
            } else {
                List<CategoryInfo> parentCategoryList = map.get(parentCategory);
                parentCategoryList.add(category);
            }
        }

        return mapper.writeValueAsString(map);
    }
}
