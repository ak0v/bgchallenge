package com.bg.restchallange.service.test;
 
import org.junit.After;
import org.junit.Before;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class GoogleDatastoreIntegrationTest {
 
		  private final LocalServiceTestHelper helper =
		      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(0));

		  @Before
		  public void setUp() {
		    helper.setUp();
		  }

		  @After
		  public void tearDown() {
		    helper.tearDown();
		  }

}
