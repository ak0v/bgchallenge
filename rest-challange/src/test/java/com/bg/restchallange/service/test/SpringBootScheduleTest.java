package com.bg.restchallange.service.test;
 
import org.junit.Test;
import org.junit.runner.RunWith; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bg.restchallange.model.Price;
import com.bg.restchallange.service.IEXConsumerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootScheduleTest { 
	@Autowired
	IEXConsumerService service;
	
	@Test
	public void testScheduledTask(){
		Price price = service.getPriceResponse("AAPL");
		System.out.println(price.getValue().toString() + " " + price.getTimestamp().toString());
	}
}
