package com.bg.restchallange.service;

import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bg.restchallange.model.Company;
import com.bg.restchallange.model.Price;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;  
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreException;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.PathElement;

/**
 * @author ak0v
 *
 */
/**
 * @author ak0v
 *
 */
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

	public Company getCompany(String symbol) throws Exception{
		Entity entity = datastore.get(Key.newBuilder("bulorosinterview", "Company", symbol).build());
		if(entity == null){
			throw new Exception("bad request symbol");
		}
		Company cmp = new Company();
		cmp.setSymbol(entity.getValue("symbol").get().toString());
		cmp.setWebsite(entity.getValue("website").get().toString());
		cmp.setCompanyName(entity.getValue("companyName").get().toString());
		logger.info(String.format("Set companny entity '%s'",cmp.getSymbol()));
		return cmp;
	}
	
	public List<Price> getPrices(String symbol) throws DatastoreException{
		List<Price> prices = new ArrayList<Price>();
		Query<Entity> query = Query.newEntityQueryBuilder()
				.setKind("Price")
				.setFilter(PropertyFilter.hasAncestor(
				        datastore.newKeyFactory().setKind("Company").newKey(symbol)))
				.build();
		QueryResults<Entity> qr = datastore.run(query);
		while(qr.hasNext()){
			Price price = new Price();
			Entity entity = qr.next();
			logger.info(String.format("Price value is '%s' : ",entity.getValue("value").get().toString()));
			price.setValue(entity.getValue("value").get().toString());
			logger.info(String.format("Timestamp value is '%s' : ",entity.getValue("timestamp").get().toString()));
			price.setTimestamp(LocalDateTime.parse(entity.getValue("timestamp").get().toString()));
			logger.info(String.format("Adding price values for '%s' : '%s' ",symbol,price.toString()));
			prices.add(price);
		}
		return prices;
	}
}
