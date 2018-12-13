package com.bg.restchallange.controller;
 
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bg.restchallange.model.Company;
import com.bg.restchallange.model.IEXResponse;
import com.bg.restchallange.model.Price;
import com.bg.restchallange.service.GoogleDatastoreTemplate;

@Component
public class IEXAdapter {
	public static final Logger logger = LoggerFactory.getLogger(IEXAdapter.class);
	
	@Autowired 
	GoogleDatastoreTemplate template;
	
	public Company retrieveCompanyFromDatastore(String symbol){
		Company cmp = null;
		
		try {
			cmp = template.getCompany(symbol);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cmp;
		
	}
	
	public List<Price> retriecePricesFromDatastore(String symbol){
		
		List<Price> prcs = new ArrayList<Price>();
		
		try {
			prcs = template.getPrices(symbol);
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return prcs;
		
	}

	public IEXResponse getAdaptedResponse(String symbol) {
		// TODO Auto-generated method stub
		return new IEXResponse(retrieveCompanyFromDatastore(symbol), retriecePricesFromDatastore(symbol));
	}
	
	
}
