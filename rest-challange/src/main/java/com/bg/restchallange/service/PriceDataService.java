package com.bg.restchallange.service;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bg.restchallange.model.Price;

@Component
public class PriceDataService {
	
	private RestRequest<Price> request;
	
	public PriceDataService() {
	}
	 
	@Scheduled(fixedRate=5000)
	public Price getResponse() {
		Price price = null;
		if(request !=null){ 
		price = RestRequestHelper.getResponse(request, Price.class);
		price.setTimestamp(LocalDateTime.now());
		System.out.println(price.getValue().toString() +  "  " + price.getTimestamp().toString());
		}
		return price;
	}

	public void setRequest(RestRequest<Price> request) {
		this.request = request;
	}
	
}
