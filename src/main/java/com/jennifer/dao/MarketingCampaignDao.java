package com.jennifer.dao;

import com.jennifer.entity.MarketingCampaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * MarketingCampaign data access object interface
 */

public interface MarketingCampaignDao extends JpaRepository<MarketingCampaign, Integer> {

    MarketingCampaign findById(int id);
    List<MarketingCampaign> findAll();
    List<MarketingCampaign> findAllByOrderByIdDesc();

}
