package com.jennifer.controller.rest;

import com.jennifer.entity.MarketingCampaign;
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
 * Rest API Controller for MarketingCampaign
 */

@RestController
@RequestMapping("/api/marketing")
public class RestMarketingCampaignController {

    private static final Logger log = LoggerFactory.getLogger(RestMarketingCampaignController.class);
    private MarketingCampaignService marketingCampaignService;

    @Autowired
    public RestMarketingCampaignController(MarketingCampaignService marketingCampaignService){
        this.marketingCampaignService = marketingCampaignService;
    }

    @GetMapping
    public Object findAll() {
        log.info(" > [rest] Marketing Campaign - findAll");
        List<MarketingCampaign> marketingCampaigns = marketingCampaignService.findAllCampaigns();
        if (marketingCampaigns.isEmpty()) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        return marketingCampaigns;
    }

    @PutMapping
    public Object update(@RequestBody MarketingCampaign marketingCampaign){
        log.info(" > [rest] Marketing Campaign - update");
        MarketingCampaign marketingCampaignData = marketingCampaignService.findCampaign(marketingCampaign.getId());

        if(marketingCampaignData != null)
            return marketingCampaignService.updateCampaign(marketingCampaign);

        return new ResponseEntity<>("Unable to update!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping
    public Object delete(@RequestBody MarketingCampaign marketingCampaign){
        log.info(" > [rest] Marketing Campaign - delete");
        MarketingCampaign marketingCampaignData = marketingCampaignService.findCampaign(marketingCampaign.getId());

        try {
            if(marketingCampaignData != null)
                marketingCampaignService.deleteCampaign(marketingCampaign);
            return marketingCampaignData;
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>("Event '" + marketingCampaignData.getEvent() + "' is in used! Unable to delete!", HttpStatus.CONFLICT);
        } catch (Exception e){
            return new ResponseEntity<>("Unable to delete!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public MarketingCampaign insert(@RequestBody MarketingCampaign marketingCampaign){
        log.info(" > [rest] Marketing Campaign - insert");
        return marketingCampaignService.addCampaign(marketingCampaign);
    }

}
