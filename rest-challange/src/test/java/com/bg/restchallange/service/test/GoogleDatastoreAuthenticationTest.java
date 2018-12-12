package com.bg.restchallange.service.test;

import static org.junit.Assert.assertNotNull;


import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Bucket; 
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class GoogleDatastoreAuthenticationTest  {
	public static final org.slf4j.Logger logger = LoggerFactory.getLogger(GoogleDatastoreAuthenticationTest.class);
	@Test
	public void authImplicit() {
		  // If you don't specify credentials when constructing the client, the client library will
		  // look for credentials via the environment variable GOOGLE_APPLICATION_CREDENTIALS.
		  Storage storage = StorageOptions.getDefaultInstance().getService();

		  logger.info("Buckets loading...");
		  Page<Bucket> buckets = storage.list();
		  logger.info(String.format("Buckets found:'%s'", buckets.getValues().toString()));
		  assertNotNull(storage.getServiceAccount("bulorosinterview"));
		}
}
