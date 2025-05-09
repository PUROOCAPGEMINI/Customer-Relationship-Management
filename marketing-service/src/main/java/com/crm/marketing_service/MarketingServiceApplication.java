package com.crm.marketing_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MarketingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketingServiceApplication.class, args);
		System.out.println("Marketing Service is RUnninng.....");
	}

}
