package test.joe.spring.springapp.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import joe.spring.springapp.data.domain.Account;
import joe.spring.springapp.data.domain.Customer;
import joe.spring.springapp.data.jpa.DbHibernateConfig;
import joe.spring.springapp.services.AccountService;
import joe.spring.springapp.services.CustomerService;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * A simple test class.
 * 
 * @author jsicree
 * 
 */
@RunWith(JUnit4.class)
public class CreateCustomerData {

	protected final static Logger log = LoggerFactory
			.getLogger(CreateCustomerData.class);

	private static CustomerService customerService;
	private static AccountService accountService;
	private static AbstractApplicationContext context; 
	
	public static String[][] customerData = {
		{"John", "Smith", "jsmith01", "08/21/1971"},
		{"Jane", "Riggs", "jriggs01", "10/15/1980"},
		{"Bill", "Nye", "sciguy1234", "03/25/1962"},
		{"Alex", "Lifeson", "lerxst", "08/27/1953"},
		{"Geddy", "Lee", "dirk", "07/29/1953"},
		{"Neil", "Peart", "pratt", "09/12/1952"}
	};

	public static String[][] accountData = {
		{"jsmith01", "FREE", "F1234-56789"},
		{"jsmith01", "PREMIUM", "P1234-56789"},
		{"jriggs01", "FREE", "F2345-67890"},
		{"sciguy1234", "PREMIUM", "P3456-78901"},
		{"lerxst", "PREMIUM", "P4567-89012"},
		{"dirk", "PREMIUM", "P5678-90123"},
		{"pratt", "PREMIUM", "P6789-01234"}
	};
	
	@BeforeClass
	public static void setup() {

		context = new AnnotationConfigApplicationContext(
				DbHibernateConfig.class);
		customerService = (CustomerService) context.getBean("customerService");
		accountService = (AccountService) context.getBean("accountService");

		accountService.removeAllAccounts();
		customerService.removeAllCustomers();
	}

	@Ignore
	@Test
	public void createCustomerAndAccounts() {

		log.info(">> Entering createCustomerAndAccounts.");

		log.info("Removing existing accounts.");
		accountService.removeAllAccounts();

		log.info("Removing existing customers.");
		customerService.removeAllCustomers();

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		try {
			log.info("Adding test customers.");
			for (int x = 0; x < customerData.length; x++) {
				log.info("Adding customer " + customerData[x][0] + " " + customerData[x][1]);
				customerService.createCustomer(customerData[x][0], customerData[x][1],customerData[x][2], dateFormat.parse(customerData[x][3]));				
			}			
			
			log.info("Adding test accounts.");
			for (int x = 0; x < accountData.length; x++) {
				Customer c = customerService.getCustomerByUserName(accountData[x][0]);				
				if (c != null) {
					log.info("Adding " + accountData[x][1] + " account for customer " + accountData[x][0]);
					accountService.createAccount(Account.AccountType.valueOf(accountData[x][1]), accountData[x][2], c);
					
				} else {
					log.error("Unable to find customer " + accountData[x][0]);
				}
			}			
			
		} catch (ParseException pe) {
			// Do nothing. :)
		}
		log.info("<< Leaving createCustomerAndAccounts.");
	}

	@Test 
	public void removeCustomersAndAccounts() {

		log.info(">> Entering removeCustomersAndAccounts.");
		accountService.removeAllAccounts();
		customerService.removeAllCustomers();
		log.info("<< Leaving removeCustomersAndAccounts.");
	}

	@AfterClass
	public static void after() {
		log.info("In after().");
		if (context != null) {
			context.close();
		}
	}

}
