package com.jennifer.controller.rest.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.jennifer.entity.CampaignProduct;
import com.jennifer.entity.MarketingCampaign;
import com.jennifer.entity.ProductInfo;
import com.jennifer.service.CampaignProductService;
import com.jennifer.service.MarketingCampaignService;
import com.jennifer.service.ProductInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Deserializer for ProductInfo
 */

public class CampaignProductDeserializer extends JsonDeserializer<CampaignProduct> {

    private static final Logger log = LoggerFactory.getLogger(CampaignProductDeserializer.class);
    private CampaignProductService campaignProductService;
    private MarketingCampaignService marketingCampaignService;
    private ProductInfoService productInfoService;

    @Autowired
    public CampaignProductDeserializer(CampaignProductService campaignProductService, MarketingCampaignService marketingCampaignService, ProductInfoService productInfoService) {
        this.campaignProductService = campaignProductService;
        this.marketingCampaignService = marketingCampaignService;
        this.productInfoService = productInfoService;
    }

    @Override
    public CampaignProduct deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        CampaignProduct campaignProduct = campaignProductService.findByCampaignIdAndProductId(node.get("marketingCampaign").get("id").asInt(), node.get("productInfo").get("id").asInt());

        if (campaignProduct != null) {
            campaignProduct.setDiscount(node.get("discount").asInt());
            return campaignProduct;
        } else {
            MarketingCampaign marketingCampaign = marketingCampaignService.findCampaign(node.get("marketingCampaign").get("id").asInt());
            ProductInfo productInfo = productInfoService.findProduct(node.get("productInfo").get("id").asInt());

            CampaignProduct newCampaignProduct = new CampaignProduct(marketingCampaign, productInfo, node.get("discount").asInt());
            return newCampaignProduct;
        }


    }

}
