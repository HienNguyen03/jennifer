package com.jennifer.controller.rest.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.jennifer.entity.CategoryInfo;
import com.jennifer.entity.ProductInfo;

import java.io.IOException;
import java.util.List;

/**
 * Serializer for CategoryInfo
 */

public class ProductInfoSerializer extends StdSerializer<List> {

    public ProductInfoSerializer() {
        this(null);
    }

    public ProductInfoSerializer(Class<List> t) {
        super(t);
    }

    @Override
    public void serialize(List list, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        List<ProductInfo> productInfos = (List<ProductInfo>) list;
        jgen.writeStartArray(); // [
        for (ProductInfo productInfo : productInfos) {
            jgen.writeStartObject(); // {
            jgen.writeNumberField("id", productInfo.getId()); // "id" : "value1"
            jgen.writeStringField("name", productInfo.getName()); // "name" : "value2"
            jgen.writeNumberField("unitPrice", productInfo.getUnitPrice()); // "unitPrice" : "value1"
            jgen.writeNumberField("discount", productInfo.getDiscount()); // "discount" : "value2"
            jgen.writeStringField("image", productInfo.getImage()); // "image" : "value2"
            jgen.writeNumberField("quantity", productInfo.getQuantity()); // "quantity" : "value1"
            jgen.writeStringField("description", productInfo.getDescription()); // "description" : "value2"
            jgen.writeStringField("detail", productInfo.getDetail()); // "detail" : "value2"
            jgen.writeStringField("status", productInfo.getStatus()); // "status" : "value2"
            jgen.writeFieldName("categoryInfo"); // categoryInfo:

            CategoryInfo categoryInfo = productInfo.getCategoryInfo();
            if (categoryInfo != null) {
                jgen.writeStartObject(); // {
                jgen.writeNumberField("id", categoryInfo.getId()); // "id" : "value1"
                jgen.writeStringField("name", categoryInfo.getName()); // "name" : "value2"
                //jgen.writeNumberField("placeOrder", categoryInfo.getPlaceOrder()); // "placeOrder" : "value3"
                jgen.writeEndObject(); // }
            } else {
                jgen.writeNull();
            }
            jgen.writeEndObject(); // }
        }
        jgen.writeEndArray(); // ]
    }
}
