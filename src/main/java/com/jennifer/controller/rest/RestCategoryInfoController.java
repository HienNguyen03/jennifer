package com.jennifer.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jennifer.controller.rest.serializer.CategoryInfoSameLevelSerializer;
import com.jennifer.controller.rest.serializer.CategoryInfoSelect2Serializer;
import com.jennifer.controller.rest.serializer.CategoryInfoSerializer;
import com.jennifer.entity.CategoryInfo;
import com.jennifer.service.CategoryInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Rest API Controller for CategoryInfo
 */

@RestController
public class RestCategoryInfoController {

    private static final Logger log = LoggerFactory.getLogger(RestCategoryInfoController.class);
    private CategoryInfoService categoryInfoService;

    @Autowired
    public RestCategoryInfoController(CategoryInfoService categoryInfoService) {
        this.categoryInfoService = categoryInfoService;
    }

    @GetMapping("/api/category-select2")
    public Object findAllForSelect() throws JsonProcessingException {
        log.info(" > [rest] Category - findAllForSelect");
        List<CategoryInfo> categoryInfoList = categoryInfoService.findAllCategories();
        if (categoryInfoList.isEmpty()) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Map.class, new CategoryInfoSelect2Serializer());
        //mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
        mapper.registerModule(module);

        Map<CategoryInfo, List<CategoryInfo>> map = new HashMap<>();
        for (CategoryInfo category : categoryInfoList) {
            CategoryInfo parentCategory = category.getSuperCategoryInfo();
            if (parentCategory == null) {
                map.put(category, new ArrayList<>());
            } else {
                List<CategoryInfo> parentCategoryList = map.get(parentCategory);
                parentCategoryList.add(category);
            }
        }

        return mapper.writeValueAsString(map);
    }

    @GetMapping("/api/category")
    public Object findAll() throws JsonProcessingException {
        log.info(" > [rest] Category - findAll");
        List<CategoryInfo> categoryInfoList = categoryInfoService.findAllCategories();
        if (categoryInfoList.isEmpty()) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        for(CategoryInfo c : categoryInfoList){
            log.info(c.toString());
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(List.class, new CategoryInfoSerializer());
        mapper.registerModule(module);

        return mapper.writeValueAsString(categoryInfoList);
    }

    @GetMapping("/api/category-same-level")
    public Object findAllSameLevel() throws JsonProcessingException {
        log.info(" > [rest] Category - findAllSameLevel");
        List<CategoryInfo> categoryInfoList = categoryInfoService.findAllCategories();
        if (categoryInfoList.isEmpty()) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        for(CategoryInfo c : categoryInfoList){
            log.info(c.toString());
        }

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(List.class, new CategoryInfoSameLevelSerializer());
        mapper.registerModule(module);

        return mapper.writeValueAsString(categoryInfoList);
    }

    @PutMapping("/api/category")
    public Object update(@RequestBody CategoryInfo categoryInfo){
        log.info(" > [rest] Category - update");
        CategoryInfo categoryInfoData = categoryInfoService.findCategory(categoryInfo.getId());

        if (categoryInfoData != null)
            if (categoryInfo.getSuperCategoryInfo() != null)
                if (categoryInfo.getId() != categoryInfo.getSuperCategoryInfo().getId())
                    return categoryInfoService.updateCategory(categoryInfo);
                else
                    return new ResponseEntity<>("Category '" + categoryInfo.getName() + "' cannot be super category itself!", HttpStatus.CONFLICT);
            else
                return categoryInfoService.updateCategory(categoryInfo);

        return new ResponseEntity<>("Unable to update!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/api/category")
    public Object delete(@RequestBody CategoryInfo categoryInfo){
        log.info(" > [rest] Category - delete");
        CategoryInfo categoryInfoData = categoryInfoService.findCategory(categoryInfo.getId());

        try {
            if (categoryInfoData != null)
                categoryInfoService.deleteCategory(categoryInfo);
            return categoryInfoData;
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>("Category '" + categoryInfoData.getName() + "' is in used! Unable to delete!", HttpStatus.CONFLICT);
        } catch (Exception e){
            return new ResponseEntity<>("Unable to delete!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/category")
    public CategoryInfo insert(@RequestBody CategoryInfo categoryInfo){
        log.info(" > [rest] Category - insert");
        return categoryInfoService.addCategory(categoryInfo);
    }
}
