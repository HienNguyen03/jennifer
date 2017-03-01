package com.jennifer.controller.rest.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.jennifer.entity.CategoryInfo;

import java.io.IOException;
import java.util.List;

/**
 * Serializer for CategoryInfo
 */

public class CategoryInfoSameLevelSerializer extends StdSerializer<List> {

    public CategoryInfoSameLevelSerializer() {
        this(null);
    }

    public CategoryInfoSameLevelSerializer(Class<List> t) {
        super(t);
    }

    @Override
    public void serialize(List list, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject(); // {
        jgen.writeFieldName("items"); // items:
        List<CategoryInfo> categoryInfos = (List<CategoryInfo>) list;
        jgen.writeStartArray(); // [
        for(CategoryInfo categoryInfo : categoryInfos) {
            jgen.writeStartObject(); // {
            jgen.writeNumberField("id", categoryInfo.getId()); // "id" : "value1"
            jgen.writeStringField("text", categoryInfo.getName()); // "name" : "value2"
            jgen.writeNumberField("placeOrder", categoryInfo.getPlaceOrder()); // "placeOrder" : "value2"
            jgen.writeEndObject(); // }
        }
        jgen.writeEndArray(); // ]
        jgen.writeEndObject(); // }
    }
}
