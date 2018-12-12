package com.bg.restchallange;

import com.bg.restchallange.model.Company;
import com.bg.restchallange.service.IEXComponent;
import com.bg.restchallange.service.IEXConsumerService;
import com.bg.restchallange.service.MethodType;
import com.bg.restchallange.service.RestRequest;
import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.StringValue;

public class App {

	public static void main(String[] args) {

		IEXComponent service = new IEXComponent();
		
		Company cmp = service.getCompanies();
		
		System.out.println(cmp.getCompanyName());
		
		// Create an authorized Datastore service using Application Default Credentials.
		final Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

		// Create a Key factory to construct keys associated with this project.
		final KeyFactory keyFactory = datastore.newKeyFactory().setKind("Company");
		
		Key key = datastore.allocateId(keyFactory.newKey());
		System.out.println(key.getKind());
		Entity company = Entity.newBuilder(key)
		      .set("symbol",cmp.getSymbol())
		      .set("companyName",cmp.getCompanyName())
		      .set("website",cmp.getWebsite())
		      .build();
		System.out.println(company.getValue("website").toString());
		  datastore.put(company);
	}

}
