package com.crm.marketing_service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.marketing_service.models.MarketingCampaign;

@Service
public interface MarketingCampaignService {
    public List<MarketingCampaign> viewCampaigns();
    public String addCampaign(MarketingCampaign marketingCampaign);
}
