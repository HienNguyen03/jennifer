package com.jennifer.controller.rest.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.jennifer.entity.CampaignProduct;
import com.jennifer.entity.CategoryInfo;
import com.jennifer.entity.ProductInfo;

import java.io.IOException;
import java.util.List;

/**
 * Serializer for CategoryInfo
 */

public class CampaignProductSerializer extends StdSerializer<List> {

    public CampaignProductSerializer() {
        this(null);
    }

    public CampaignProductSerializer(Class<List> t) {
        super(t);
    }

    @Override
    public void serialize(List list, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        List<CampaignProduct> campaignProducts = (List<CampaignProduct>) list;
        jgen.writeStartArray(); // [
        for (CampaignProduct campaignProduct : campaignProducts) {
            jgen.writeStartObject(); // {
            jgen.writeNumberField("discount", campaignProduct.getDiscount()); // "discount" : "value1"

            jgen.writeFieldName("productInfo");
            jgen.writeStartObject(); // {
            jgen.writeNumberField("id", campaignProduct.getProductInfo().getId()); // "id" : "value1"
            jgen.writeStringField("name", campaignProduct.getProductInfo().getName()); // "name" : "value2"
            jgen.writeNumberField("unitPrice", campaignProduct.getProductInfo().getUnitPrice()); // "unitPrice" : "value3"
            jgen.writeNumberField("quantity", campaignProduct.getProductInfo().getQuantity()); // "quantity" : "value3"
            jgen.writeEndObject(); // }

            jgen.writeFieldName("marketingCampaign");
            jgen.writeStartObject(); // {
            jgen.writeNumberField("id", campaignProduct.getMarketingCampaign().getId()); // "id" : "value1"
            jgen.writeStringField("event", campaignProduct.getMarketingCampaign().getEvent()); // "event" : "value2"
            jgen.writeEndObject(); // }

            jgen.writeEndObject(); // }
        }
        jgen.writeEndArray(); // ]
    }
}
