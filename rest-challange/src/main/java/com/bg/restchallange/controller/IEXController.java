package com.bg.restchallange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import com.bg.restchallange.model.IEXResponse; 

@RestController
public class IEXController {
	
	@Autowired 
	IEXAdapter adapter;
	
	@RequestMapping("/iex") 
	public IEXResponse getIEXResponse(
			@RequestParam("symbol") String symbol){
		IEXResponse response = adapter.getAdaptedResponse(symbol);
				return response;
		
	}
	
	
}
