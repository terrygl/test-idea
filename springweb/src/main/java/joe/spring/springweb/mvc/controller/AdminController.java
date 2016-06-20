package joe.spring.springweb.mvc.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import joe.spring.springapp.data.domain.Account;
import joe.spring.springapp.data.domain.Customer;
import joe.spring.springapp.data.reference.Title;
import joe.spring.springapp.services.AccountService;
import joe.spring.springapp.services.CustomerService;
import joe.spring.springapp.services.ReferenceService;
import joe.spring.springweb.mvc.data.DropDownData;
import joe.spring.springweb.mvc.data.FormFieldError;
import joe.spring.springweb.mvc.data.ValidationResponse;
import joe.spring.springweb.mvc.model.CustomerModel;

/**
 * Handles requests for the form page examples.
 */
@Controller
public class AdminController {

	@Autowired
	protected CustomerService customerService;

	@Autowired
	protected AccountService accountService;

	@Autowired
	protected ReferenceService refService;

	@Autowired
	@Qualifier("customerModelValidator")
	private Validator validator;

	@Autowired
	protected MessageSource messageSource;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	protected final static Logger log = LoggerFactory.getLogger(AdminController.class);

	public static String[][] customerData = { { "John", "Smith", "jsmith01", "08/21/1971" },
			{ "Jane", "Riggs", "jriggs01", "10/15/1980" }, { "Bill", "Nye", "sciguy1234", "03/25/1962" },
			{ "Alex", "Lifeson", "lerxst", "08/27/1953" }, { "Geddy", "Lee", "dirk", "07/29/1953" },
			{ "Neil", "Peart", "pratt", "09/12/1952" } };

	public static String[][] accountData = { { "jsmith01", "FREE", "F1234-56789" },
			{ "jsmith01", "PREMIUM", "P1234-56789" }, { "jriggs01", "FREE", "F2345-67890" },
			{ "sciguy1234", "PREMIUM", "P3456-78901" }, { "lerxst", "PREMIUM", "P4567-89012" },
			{ "dirk", "PREMIUM", "P5678-90123" }, { "pratt", "PREMIUM", "P6789-01234" } };

	public AdminController() {

	}

	// Displays a list of all customers.

	@RequestMapping(value = "/admin")
	public String home() {
		log.info("Displaying the admin page.");

		return "admin";
	}

	@RequestMapping(value = "/createCustomerAccountData", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse createAccountCustomerData() {

		ValidationResponse response = new ValidationResponse();
		response.setStatus("OK");
		List<FormFieldError> errorList = new ArrayList<FormFieldError>();
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
					response.setStatus("ERROR");
				}
			}			
			
		} catch (ParseException pe) {
			// Do nothing. :)
		}
		
		return response;
	}

	@RequestMapping(value = "/deleteCustomerAccountData", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse deleteAccountCustomerData() {
		ValidationResponse response = new ValidationResponse();
		log.debug("Deleting Accounts");
		accountService.removeAllAccounts();
		log.debug("Deleting Customers");
		customerService.removeAllCustomers();
		response.setStatus("OK");
		return response;
	}

}