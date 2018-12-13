package com.bg.restchallange.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bg.restchallange.model.Company; 

@Component
public class IEXComponent {
	public static final Logger logger = LoggerFactory.getLogger(IEXComponent.class);
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Value("${predefined.symbols}")
	String[] symbols;
	
	@Autowired
	GoogleDatastoreTemplate template;
	
	 
	@Scheduled(fixedRate=500000)
	public void getIAXData(){
		for(String symbol : symbols){
		logger.info(String.format("Running services for: '%s'",symbol));
		fetchCompanies(symbol);
		fetchPrice(symbol);
		}
	}
	
	public void fetchCompanies(String symbol){
		Company cmp = restTemplate.getForObject("https://api.iextrading.com/1.0/stock/{symbol}/company", Company.class,symbol);
		logger.info("Company fetched:" + cmp.getCompanyName());
		template.persistCompany(cmp);
	}
	
	
	public void fetchPrice(String symbol){
		String price = restTemplate.getForObject("https://api.iextrading.com/1.0/stock/{symbol}/price", String.class,symbol);
		logger.info("Price fetched:" + price);
		template.persistPriceData(price, symbol);
	}
}
