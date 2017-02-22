package com.jennifer.controller.rest.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.jennifer.entity.CategoryInfo;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Serializer for CategoryInfo
 */

public class CategoryInfoSerializer extends StdSerializer<Map> {

    public CategoryInfoSerializer() {
        this(null);
    }

    public CategoryInfoSerializer(Class<Map> t) {
        super(t);
    }

//    @Override
//    public void serialize(CategoryInfo item, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
//        jgen.writeStartObject();
//        jgen.writeNumberField("id", item.getId());
//        jgen.writeStringField("text", item.getName());
//        if(item.getSuperCategoryInfo() != null){
//            jgen.writeFieldName("children");
//            jgen.writeStartObject();
//            jgen.writeNumberField("id", item.getId());
//            jgen.writeStringField("text", item.getName());
//            jgen.writeEndObject();
//            jgen.writeStringField("superCategoryName", item.getSuperCategoryInfo().getName());
//        }
//
//        jgen.writeEndObject();
//    }

    @Override
    public void serialize(Map map, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject(); // {
        jgen.writeFieldName("items"); // items:
        Iterator it = map.entrySet().iterator();
        jgen.writeStartArray(); // [
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            it.remove(); // avoids a ConcurrentModificationException

            CategoryInfo categoryInfo = (CategoryInfo)pair.getKey();
            List<CategoryInfo> categoryInfoList = (List<CategoryInfo>)pair.getValue();

            //jgen.writeObjectField();
            jgen.writeStartObject(); // {
            jgen.writeNumberField("id", categoryInfo.getId()); // "id" : "value1"
            jgen.writeStringField("text", categoryInfo.getName()); // "text" : "value2"
            if (!categoryInfoList.isEmpty()){
                jgen.writeFieldName("children"); // children:
                jgen.writeStartArray(); // [
                for (CategoryInfo item : categoryInfoList) {
                    jgen.writeStartObject(); // {
                    jgen.writeNumberField("id", item.getId()); // "id" : "value1"
                    jgen.writeStringField("text", item.getName()); // "text" : "value2"
                    jgen.writeEndObject(); // }
                }
                jgen.writeEndArray(); // ]
            }
            jgen.writeEndObject(); // }

        }
        jgen.writeEndArray(); // ]
        jgen.writeEndObject(); // }
    }
}
