package com.bg.restchallange.service.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bg.restchallange.controller.IEXAdapter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IEXAdapterTest {

	@Autowired
	IEXAdapter adapter;
	
	@Test
	public void testRetrieveCompanyFromDatastore(){
		
		assertEquals(adapter.retrieveCompanyFromDatastore("AAPL").getSymbol(),"AAPL");
		assertEquals(adapter.retrieveCompanyFromDatastore("AAPL").getCompanyName(),"Apple Inc.");
		assertEquals(adapter.retrieveCompanyFromDatastore("AAPL").getWebsite(),"http://www.apple.com");
		
	}
	
	@Test
	public void testRetrievePricesFromDatastore(){
		
		assertEquals(adapter.retriecePricesFromDatastore("AAPL").get(0).getValue(),"169.1");
	}
}
