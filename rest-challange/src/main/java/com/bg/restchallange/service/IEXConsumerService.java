package com.bg.restchallange.service;
 
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bg.restchallange.model.Company;
import com.bg.restchallange.model.Price; 

@Component
public class IEXConsumerService {  

	RestRequest<Price> request = null;
	
	public Company getCompanyResponse(String symbol){
		Company cmp = RestRequestHelper.getResponse(new RestRequest<Company>("/company",MethodType.GET,symbol,""),Company.class);
		return cmp;
	}
	
	public Price getPriceResponse(String symbol){
		request = new RestRequest<Price>("/price", MethodType.GET, symbol, "");
		Price price = getResponse();
		return price;
	}
	
	@Scheduled(fixedRate=5000)
	public Price getResponse() { 
		Price price = RestRequestHelper.getResponse(request, Price.class);
		price.setTimestamp(LocalDateTime.now());
		System.out.println(price.getValue().toString() +  "  " + price.getTimestamp().toString());
		 
		return price;
	}
}
