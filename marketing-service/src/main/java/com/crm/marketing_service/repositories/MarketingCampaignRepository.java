package com.crm.marketing_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.marketing_service.models.MarketingCampaign;

@Repository
public interface MarketingCampaignRepository extends JpaRepository<MarketingCampaign, Long> {
    
}
