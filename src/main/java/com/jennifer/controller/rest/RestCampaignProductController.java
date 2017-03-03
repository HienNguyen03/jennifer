package com.jennifer.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jennifer.controller.rest.serializer.CampaignProductSerializer;
import com.jennifer.entity.CampaignProduct;
import com.jennifer.entity.MarketingCampaign;
import com.jennifer.service.CampaignProductService;
import com.jennifer.service.MarketingCampaignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for CampaignProduct activities
 */

@RestController
@RequestMapping("/api/campaign-product")
public class RestCampaignProductController {

    private static final Logger log = LoggerFactory.getLogger(RestCampaignProductController.class);
    private MarketingCampaignService marketingCampaignService;
    private CampaignProductService campaignProductService;

    @Autowired
    public RestCampaignProductController(MarketingCampaignService marketingCampaignService, CampaignProductService campaignProductService){
        this.marketingCampaignService = marketingCampaignService;
        this.campaignProductService = campaignProductService;
    }

    @GetMapping("/{campaignId}")
    public Object findCampaignProducts(@PathVariable("campaignId") Integer id) throws JsonProcessingException {
        log.info(" > [rest] Campaign Product - findCampaignProducts");
        MarketingCampaign marketingCampaign = marketingCampaignService.findCampaign(id);
        List<CampaignProduct> campaignProducts;

        if (marketingCampaign != null){
            campaignProducts = campaignProductService.findAllByCampaignId(marketingCampaign.getId());
            if(!campaignProducts.isEmpty()){
                ObjectMapper mapper = new ObjectMapper();
                SimpleModule module = new SimpleModule();
                module.addSerializer(List.class, new CampaignProductSerializer());
                mapper.registerModule(module);

                return mapper.writeValueAsString(campaignProducts);
            }
        }

        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/")
    public Object findCampaignProductsWithoutId() throws JsonProcessingException {
        log.info(" > [rest] Campaign Product - findCampaignProductsWithoutId");
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public Object update(@RequestBody CampaignProduct campaignProduct){
        log.info(" > [rest] Campaign Product - update");
        CampaignProduct campaignProductData = campaignProductService.findByCampaignIdAndProductId(campaignProduct.getMarketingCampaign().getId(), campaignProduct.getProductInfo().getId());

        if(campaignProductData != null)
            return campaignProductService.updateCampaignProduct(campaignProduct);

        return new ResponseEntity<>("Unable to update!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping
    public CampaignProduct insert(@RequestBody CampaignProduct campaignProduct){
        log.info(" > [rest] Campaign Product - insert");
        return campaignProductService.addCampaignProduct(campaignProduct);
    }

    @DeleteMapping
    public Object delete(@RequestBody CampaignProduct campaignProduct){
        log.info(" > [rest] Campaign Product - delete");
        CampaignProduct campaignProductData = campaignProductService.findByCampaignIdAndProductId(campaignProduct.getMarketingCampaign().getId(), campaignProduct.getProductInfo().getId());

        try {
            if(campaignProductData != null)
                campaignProductService.deleteCampaignProduct(campaignProductData);
            return campaignProductData;
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>("Campaign product '" + campaignProductData.getProductInfo().getName() + "' is in used! Unable to delete!", HttpStatus.CONFLICT);
        } catch (Exception e){
            return new ResponseEntity<>("Unable to delete!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
