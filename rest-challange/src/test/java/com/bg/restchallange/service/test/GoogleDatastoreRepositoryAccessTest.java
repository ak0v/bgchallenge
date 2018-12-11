package com.bg.restchallange.service.test;
import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import static org.junit.Assert.assertEquals;
 
import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query; 

public class GoogleDatastoreRepositoryAccessTest extends GoogleDatastoreIntegrationTest {

	public static final Logger logger = Logger.getLogger(GoogleDatastoreRepositoryAccessTest.class);
	
	@Test
	public void doTestAccessToGoogleDatastore(){
		logger.info("instantiating datastore...");
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		logger.info("adding test entities...");
		ds.put(new Entity("yam"));
		ds.put(new Entity("yam"));
		assertEquals(2, ds.prepare(new Query("yam")).countEntities(withLimit(10)));
	}
}
