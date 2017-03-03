package com.jennifer.service.impl;

import com.jennifer.dao.CampaignProductDao;
import com.jennifer.entity.CampaignProduct;
import com.jennifer.service.CampaignProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MarketingCampaign service implementation
 */

@Service("campaignProductService")
public class CampaignProductServiceImpl implements CampaignProductService {

    private static final Logger log = LoggerFactory.getLogger(CampaignProductServiceImpl.class);

    private CampaignProductDao campaignProductDao;

    @Autowired
    public CampaignProductServiceImpl(CampaignProductDao campaignProductDao) {
        this.campaignProductDao = campaignProductDao;
    }

    @Override
    public List<CampaignProduct> findAllCampaignProducts() {
        return campaignProductDao.findAll();
    }

    @Override
    public CampaignProduct updateCampaignProduct(CampaignProduct campaignProduct) {
        return campaignProductDao.save(campaignProduct);
    }

    @Override
    public void deleteCampaignProduct(CampaignProduct campaignProduct) {
        campaignProductDao.delete(campaignProduct);
    }

    @Override
    public CampaignProduct addCampaignProduct(CampaignProduct campaignProduct) {
        return campaignProductDao.save(campaignProduct);
    }

    @Override
    public List<CampaignProduct> findAllByCampaignId(int campaignId) {
        return campaignProductDao.findAllByCampaignId(campaignId);
    }

    @Override
    public CampaignProduct findByCampaignIdAndProductId(int campaignId, int productId) {
        return campaignProductDao.findByCampaignIdAndProductId(campaignId, productId);
    }
}
