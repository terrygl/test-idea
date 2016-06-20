package test.joe.spring.springapp.services;

import java.util.Date;

import joe.spring.springapp.data.domain.Account;
import joe.spring.springapp.data.domain.Customer;
import joe.spring.springapp.data.domain.Account.AccountType;
import joe.spring.springapp.data.jpa.DbHibernateConfig;
import joe.spring.springapp.services.AccountService;
import joe.spring.springapp.services.CustomerService;

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
public class AccountServiceTest {

	protected final static Logger log = LoggerFactory.getLogger(AccountServiceTest.class); 

	private static CustomerService customerService;
	private static AccountService accountService;
	private static AbstractApplicationContext context;
	
	@BeforeClass
	public static void setup() {

		context = new AnnotationConfigApplicationContext(DbHibernateConfig.class);		
		customerService = (CustomerService) context
				.getBean("customerService");		

		accountService = (AccountService) context
				.getBean("accountService");		
		
		accountService.removeAllAccounts();
		customerService.removeAllCustomers();		
	}

	@Test
	public void createAccounts() {

		log.info(">> Entering createAccounts.");
		
		Customer c = customerService.createCustomer("John", "Doe", "jdoe", new Date());
		org.junit.Assert.assertNotNull("Customer was null.", c);
		org.junit.Assert.assertNotNull("Customer Id was null.", c.getId());
		log.info("Customer created: " + c);

		log.info("Testing createAccount for Free account.");				
		Account freeAccount = accountService.createAccount(AccountType.FREE, "1234567890F", c);
		org.junit.Assert.assertNotNull("Account was null.", freeAccount);
		org.junit.Assert.assertNotNull("Account Id was null.", freeAccount.getId());
		log.info("Free account created: " + freeAccount);

		log.info("Testing createAccount for Premium account.");				
		Account premiumAccount = accountService.createAccount(AccountType.PREMIUM, "1234567890P", c);
		org.junit.Assert.assertNotNull("Account was null.", premiumAccount);
		org.junit.Assert.assertNotNull("Account Id was null.", premiumAccount.getId());
		log.info("Premium account created: " + premiumAccount);
		
		accountService.removeAllAccounts();
		customerService.removeAllCustomers();		
		log.info("<< Leaving createAccounts.");
	}
	
	
	@AfterClass
	public static void after() {
		if (context != null) {
			context.close();
		}
	}

}
