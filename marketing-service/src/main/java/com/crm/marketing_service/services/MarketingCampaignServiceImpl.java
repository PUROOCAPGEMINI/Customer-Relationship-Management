package com.crm.marketing_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.crm.marketing_service.models.MarketingCampaign;
import com.crm.marketing_service.repositories.MarketingCampaignRepository;

@Service
public class MarketingCampaignServiceImpl implements MarketingCampaignService{

    @Autowired
    MarketingCampaignRepository marketingCampaignRepository;

    @Override
    public List<MarketingCampaign> viewCampaigns() {
        return marketingCampaignRepository.findAll();
    }

    @Override
    public String addCampaign(MarketingCampaign marketingCampaign) {
        MarketingCampaign marketingCampaign1 = new MarketingCampaign();

        marketingCampaign1.setName(marketingCampaign.getName());
        marketingCampaign1.setDescription(marketingCampaign.getDescription());
        marketingCampaign1.setStartDate(marketingCampaign.getStartDate());
        marketingCampaign1.setEndDate(marketingCampaign.getEndDate());
        marketingCampaign1.setTargetAudience(marketingCampaign.getTargetAudience());
        marketingCampaign1.setStatus(marketingCampaign.getStatus());
        marketingCampaignRepository.save(marketingCampaign1);
        return "Saved Successfully";
    }

    
}
