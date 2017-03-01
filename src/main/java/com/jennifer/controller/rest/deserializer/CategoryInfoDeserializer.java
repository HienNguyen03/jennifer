package com.jennifer.controller.rest.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.jennifer.entity.CategoryInfo;
import com.jennifer.entity.ProductInfo;
import com.jennifer.service.CategoryInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Deserializer for ProductInfo
 */

public class CategoryInfoDeserializer extends JsonDeserializer<CategoryInfo> {

    private static final Logger log = LoggerFactory.getLogger(CategoryInfoDeserializer.class);
    private CategoryInfoService categoryInfoService;

    @Autowired
    public CategoryInfoDeserializer(CategoryInfoService categoryInfoService) {
        this.categoryInfoService = categoryInfoService;
    }

    @Override
    public CategoryInfo deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        CategoryInfo categoryInfo;
        CategoryInfo superCategoryInfo;

        if(node.get("superCategoryInfo") != null){
            superCategoryInfo = categoryInfoService.findCategory(node.get("superCategoryInfo").asInt());
        } else {
            superCategoryInfo = null;
        }

        if(node.get("id") != null) {
            categoryInfo = new CategoryInfo(node.get("id").asInt(), node.get("name").asText(), superCategoryInfo, node.get("placeOrder").asInt());
        } else {
            categoryInfo = new CategoryInfo(node.get("name").asText(), superCategoryInfo, node.get("placeOrder").asInt());
        }

        return categoryInfo;
    }

}
