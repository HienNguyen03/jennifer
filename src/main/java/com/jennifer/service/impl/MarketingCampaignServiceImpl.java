package com.jennifer.service.impl;

import com.jennifer.dao.MarketingCampaignDao;
import com.jennifer.entity.MarketingCampaign;
import com.jennifer.entity.MarketingCampaign;
import com.jennifer.service.MarketingCampaignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MarketingCampaign service implementation
 */

@Service("marketingCampaignService")
public class MarketingCampaignServiceImpl implements MarketingCampaignService {

    private static final Logger log = LoggerFactory.getLogger(MarketingCampaignServiceImpl.class);

    private MarketingCampaignDao marketingCampaignDao;

    @Autowired
    public MarketingCampaignServiceImpl(MarketingCampaignDao marketingCampaignDao) {
        this.marketingCampaignDao = marketingCampaignDao;
    }

    @Override
    public List<MarketingCampaign> findAllCampaigns() {
        return marketingCampaignDao.findAllByOrderByIdDesc();
    }

    @Override
    public MarketingCampaign findCampaign(int id) {
        return marketingCampaignDao.findById(id);
    }

    @Override
    public MarketingCampaign updateCampaign(MarketingCampaign marketingCampaign) {
        return marketingCampaignDao.save(marketingCampaign);
    }

    @Override
    public void deleteCampaign(MarketingCampaign marketingCampaign) {
        marketingCampaignDao.delete(marketingCampaign);
    }

    @Override
    public MarketingCampaign addCampaign(MarketingCampaign marketingCampaign) {
        return marketingCampaignDao.save(marketingCampaign);
    }
}
