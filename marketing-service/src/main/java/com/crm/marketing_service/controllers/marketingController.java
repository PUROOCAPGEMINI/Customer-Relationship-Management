package com.crm.marketing_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.marketing_service.models.MarketingCampaign;
import com.crm.marketing_service.services.MarketingCampaignService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/marketing")
public class marketingController {

    @Autowired
    MarketingCampaignService marketingCampaignService;

    @GetMapping("/campaigns")
    public ResponseEntity<List<MarketingCampaign>> viewCampaigns() {
        
        List<MarketingCampaign> marketingCampaigns = marketingCampaignService.viewCampaigns();

        if(marketingCampaigns.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(marketingCampaigns);
    }

    @PostMapping("/campaigns")
    public ResponseEntity<String> addCampaign(@RequestBody MarketingCampaign marketingCampaign) {
        return ResponseEntity.ok().body(marketingCampaignService.addCampaign(marketingCampaign));
    }
    
}
