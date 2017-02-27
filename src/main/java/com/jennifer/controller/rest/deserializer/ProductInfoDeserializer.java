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

public class ProductInfoDeserializer extends JsonDeserializer<ProductInfo> {

    private static final Logger log = LoggerFactory.getLogger(ProductInfoDeserializer.class);
    private CategoryInfoService categoryInfoService;

    @Autowired
    public ProductInfoDeserializer(CategoryInfoService categoryInfoService) {
        this.categoryInfoService = categoryInfoService;
    }

    @Override
    public ProductInfo deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        CategoryInfo categoryInfo = categoryInfoService.findCategory(node.get("categoryInfo").asInt());
        ProductInfo productInfo;

        log.info(" >> "+node.get("unitPrice").asText());
        log.info(" >> "+node.get("unitPrice").asLong());
        log.info(" >> "+BigDecimal.valueOf(node.get("unitPrice").asLong()));

        if(node.get("id") != null) {
            productInfo = new ProductInfo(node.get("id").asInt(), node.get("name").asText(), BigDecimal.valueOf(node.get("unitPrice").asLong()), node.get("discount").asInt(), node.get("image").asText(), node.get("quantity").asInt(), node.get("description").asText(), node.get("detail").asText(), node.get("status").asText(), categoryInfo);
        } else {
            productInfo = new ProductInfo(node.get("name").asText(), BigDecimal.valueOf(node.get("unitPrice").asLong()), node.get("discount").asInt(), node.get("image").asText(), node.get("quantity").asInt(), node.get("description").asText(), node.get("detail").asText(), node.get("status").asText(), categoryInfo);
        }

        log.info(" >> "+productInfo.getUnitPrice());

        return productInfo;
    }

}
