package com.bg.restchallange.service;

import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bg.restchallange.model.Company;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.PathElement;

@Repository
public class GoogleDatastoreTemplate {

	public static final Logger logger = LoggerFactory.getLogger(GoogleDatastoreTemplate.class);
	// Create an authorized Datastore service using Application Default Credentials.
	final Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

	
	public Set<String> getStoredCompanies(){
		Query<Entity> query = Query.newEntityQueryBuilder().setKind("Company").build();
		logger.info(query.toString());
		QueryResults<Entity> qr = datastore.run(query); 
		Set<String> cmps = new HashSet<String>();
		while(qr.hasNext()){ 
			logger.info(qr.next().getValue("symbol").get().toString());
			cmps.add(qr.next().getValue("symbol").get().toString());
		}
		
		return cmps;
	}
	
	public void persistCompany(Company company){
		final KeyFactory keyFactory = datastore.newKeyFactory().setKind("Company");
		Entity cmp = Entity.newBuilder(keyFactory.newKey(company.getSymbol()))
				  .set("symbol",company.getSymbol())
			      .set("companyName",company.getCompanyName())
			      .set("website",company.getWebsite())
			      .build();
		logger.info(String.format("Persisted company: '%s'", cmp.getValue("companyName").toString()));
		datastore.put(cmp);
	}
	
	
	public void persistPriceData(String price,String symbol){
		final KeyFactory keyFactory = datastore.newKeyFactory().addAncestor(PathElement.of("Company", symbol)).setKind("Price");
		Key key = datastore.allocateId(keyFactory.newKey()); 
		
		Entity prc = Entity.newBuilder(key)
		      .set("value",price)
		      .set("timestamp",LocalDateTime.now().toString())
		      .build();
		logger.info(String.format("Persisted price: '%s' at '%s'", prc.getValue("value").toString(),prc.getValue("timestamp").toString()));
		  datastore.put(prc);
		
	}

}
