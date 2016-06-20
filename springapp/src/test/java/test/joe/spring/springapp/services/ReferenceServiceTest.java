package test.joe.spring.springapp.services;

import java.util.ArrayList;

import joe.spring.springapp.data.jpa.DbHibernateConfig;
import joe.spring.springapp.data.reference.Country;
import joe.spring.springapp.data.reference.State;
import joe.spring.springapp.data.reference.Title;
import joe.spring.springapp.services.ReferenceService;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * A simple test class to unit test the <code>ServiceManager</code>.
 * 
 * @author jsicree
 * 
 */
@RunWith(JUnit4.class)
public class ReferenceServiceTest {

	protected final static Logger log = LoggerFactory.getLogger(ReferenceServiceTest.class); 

	private static ReferenceService referenceService;
	private static AbstractApplicationContext context;

	@BeforeClass
	public static void setup() {

		context = new AnnotationConfigApplicationContext(DbHibernateConfig.class);		
		referenceService = (ReferenceService) context
				.getBean("referenceService");		
}

	@Test
	public void getAllCountries() {

		log.info("Testing getAllCountries.");
		ArrayList<Country> countryList = (ArrayList<Country>) referenceService.getAllCountries();
		org.junit.Assert.assertNotNull("List returned by getAllCounties() was null.", countryList.size());
		org.junit.Assert.assertNotEquals("List returned by getAllCountries() was empty.", countryList.size(), 0);
		for (Country c : countryList) {
			log.info(c.toString());
		}
	}	

	@Test
	public void getCountry() {

		log.info("Testing accessors getCountryByCode and getCountryById");
		ArrayList<Country> countryList = (ArrayList<Country>) referenceService.getAllCountries();
		org.junit.Assert.assertNotNull("List returned by getAllCountries() was null.", countryList.size());
		org.junit.Assert.assertNotEquals("List returned by getAllCountries() was empty.", countryList.size(), 0);

		String countryCode = countryList.get(0).getCode();
		Country countryByCode = referenceService.getCountryByCode(countryCode);
		org.junit.Assert.assertNotNull("GetCountryByCode returned null for country code " + countryCode, countryByCode);
		
		Long countryId = countryList.get(0).getId();
		Country countryById = referenceService.getCountryById(countryId);
		org.junit.Assert.assertNotNull("GetCountryById returned null for country id " + countryById, countryById);

		Country badCountryByCode = referenceService.getCountryByCode("BAD");
		org.junit.Assert.assertNull("GetCountryByCode(BAD) returned data unexpectedly! ", badCountryByCode);
		
		
	}

	
	@Test
	public void getState() {

		log.info("Testing accessors getStateByCode and getStateById");
		ArrayList<State> stateList = (ArrayList<State>) referenceService.getAllStates();
		org.junit.Assert.assertNotNull("List returned by getAllStates() was null.", stateList.size());
		org.junit.Assert.assertNotEquals("List returned by getAllStates() was empty.", stateList.size(), 0);

		String stateCode = stateList.get(0).getCode();
		State stateByCode = referenceService.getStateByCode(stateCode);
		org.junit.Assert.assertNotNull("GetStateByCode returned null for state code " + stateCode, stateByCode);
		
		Long stateId = stateList.get(0).getId();
		State stateById = referenceService.getStateById(stateId);
		org.junit.Assert.assertNotNull("GetStateById returned null for state id " + stateById, stateById);
	}
	
	@Test
	public void getAllStates() {

		log.info("Testing getAllStates.");
		ArrayList<State> stateList = (ArrayList<State>) referenceService.getAllStates();
		org.junit.Assert.assertNotNull("List returned by getAllStates() was null.", stateList.size());
		org.junit.Assert.assertNotEquals("List returned by getAllStates() was empty.", stateList.size(), 0);
		for (State s : stateList) {
			log.info(s.toString());
		}
	}

	@Test
	public void getStatesByCountry() {

		log.info("Testing accessors getStatesByCountry");
		Country country = referenceService.getCountryByCode("USA");
		org.junit.Assert.assertNotNull("Country returned by getCountryByCode(USA) was null.", country);
		
		ArrayList<State> stateList = (ArrayList<State>) referenceService.getStatesByCountry(country);
		org.junit.Assert.assertNotNull("List returned by getStatesByCountry() was null.", stateList.size());
		org.junit.Assert.assertNotEquals("List returned by getStatesByCountry() was empty.", stateList.size(), 0);
	}

	@Test
	public void getAllTitles() {

		log.info("Testing getAllTitles.");
		ArrayList<Title> titleList = (ArrayList<Title>) referenceService.getAllTitles();
		org.junit.Assert.assertNotNull("List returned by getAllTitles() was null.", titleList.size());
		org.junit.Assert.assertNotEquals("List returned by getAllTitles() was empty.", titleList.size(), 0);
		for (Title t : titleList) {
			log.info(t.toString());
		}

	}	
	
	
	@AfterClass
	public static void after() {
		if (context != null) {
			context.close();
		}
	}

}
