package joe.spring.springweb.mvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import joe.spring.springapp.data.domain.Customer;
import joe.spring.springapp.data.reference.Title;
import joe.spring.springapp.services.CustomerService;
import joe.spring.springapp.services.ReferenceService;
import joe.spring.springweb.mvc.data.DropDownData;
import joe.spring.springweb.mvc.data.FormFieldError;
import joe.spring.springweb.mvc.data.ValidationResponse;
import joe.spring.springweb.mvc.model.AnnotatedAccountModel;
import joe.spring.springweb.mvc.model.CustomerModel;

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

/**
 * Handles requests for the form page examples.
 */
@Controller
public class CustomerController {

	@Autowired
	protected CustomerService customerService;
	
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
	protected final static Logger log = LoggerFactory
			.getLogger(CustomerController.class);

	public CustomerController() {

	}

	// Displays a list of all customers.

	@RequestMapping(value = "/customers")
	public String home() {
		log.info("Displaying the customers page.");

		return "customers";
	}

	@RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET)
	public @ResponseBody
	List<Customer> getAllCustomers() {
		log.debug("Fetching a list of all customers");
		ArrayList<Customer> customerList = (ArrayList<Customer>) customerService
				.getAllCustomers();
		log.debug("CustomerService.getCustomers() returned "
				+ customerList.size() + " customers.");
		return customerList;
	}

	// ******************************************************************

	// Customer search example. Takes a single search term and searches
	// by customer first, last and user name for matches.

	@RequestMapping(value = "/customerSearch", method = RequestMethod.GET)
	public String displayCustomerSearch() {
		log.info("Displaying customer search page");

		return "customerSearch";
	}

	@RequestMapping(value = "/customerSearch", method = RequestMethod.POST)
	public @ResponseBody
	List<Customer> searchCustomers(
			@RequestParam(value = "searchTerm", required = false) String searchTerm) {
		log.debug("Searching for customers with searchTerm = " + searchTerm);
		ArrayList<Customer> customerList = (ArrayList<Customer>) customerService
				.searchCustomers(searchTerm);
		log.debug("CustomerService.searchCustomers() returned "
				+ customerList.size() + " customers.");
		return customerList;
	}

	// ******************************************************************

	// Spring MVC example to display a customer form and process a create
	// customer request.

	@RequestMapping(value = "/createCustomer", method = RequestMethod.GET)
	public String displayCreateCustomer(Model model) {

		model.addAttribute("titleList", getTitleList());
		model.addAttribute("customerModel", new CustomerModel());
		log.info("Displaying the create customer form");

		return "createCustomer";
	}

	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
	public String createCustomer(@Validated CustomerModel customerModel,
			BindingResult result, Model model) {
		log.info("In createCustomer...");
		String dest = "createCustomerThanks";
		log.info("CustomerModel:" + customerModel);
		if (result.hasErrors()) {
			log.info("Form validation errors: " + result.getErrorCount());
			for (ObjectError oe : result.getAllErrors()) {
				log.info(oe.toString());
			}
			model.addAttribute("titleList", getTitleList());
			dest = "createCustomer";
		} else {
			log.info("NO form validation errors found. Creating a new customer!");
			//customerService.createCustomer(customerModel.getFirstName(), customerModel.getLastName(), customerModel.getUserName(), customerModel.getDob());
			model.addAttribute("firstName", customerModel.getFirstName());
		}
		return dest;
	}

	
	
	// ******************************************************************

	// Spring MVC & JQuery example to display a customer form and process a
	// create customer request.

//	@RequestMapping(value = "/createJsonCustomer", method = RequestMethod.GET)
//	public String displayCustomerForm() {
//		log.info("Displaying the new customer form");
//		return "newCustomerForm";
//	}

	@RequestMapping(value = "/createCustomerJson", method = RequestMethod.POST)
	public @ResponseBody
	ValidationResponse createCustomerJson(
			@Validated CustomerModel customerModel, BindingResult result,
			Model model) {

		log.info("In createCustomerJson...");
		ValidationResponse response = new ValidationResponse();
		log.info("CustomerModel:" + customerModel);
		if (result.hasErrors()) {
			response.setStatus("ERROR");
			List<FormFieldError> errorList = new ArrayList<FormFieldError>();
			// Validation - get the current locale to use to look up error messages
			Locale currentLocale = LocaleContextHolder.getLocale();
			log.info("Form validation errors: " + result.getErrorCount());
			for (ObjectError oe : result.getAllErrors()) {
				log.info(((FieldError) oe).toString());
				// Look up the localized error message and create a FormFieldError with it.
		        String localizedErrorMessage = messageSource.getMessage((FieldError) oe, currentLocale);
				errorList.add(new FormFieldError(((FieldError) oe).getField(),
						localizedErrorMessage));
			}
			response.setErrorMessageList(errorList);
		} else {
			response.setStatus("OK");
			log.info("NO form validation errors found.");
			// TODO: With no validation errors, time to create a new customer.
		}
		return response;
	}
	
	private List<DropDownData> getTitleList() {
		List<Title> titleList = new ArrayList<Title>();
		List<DropDownData> titleDropDownList = new ArrayList<DropDownData>();
		titleList = refService.getAllTitles();
		for (Title t : titleList) {
			titleDropDownList.add(new DropDownData(t.id(), t.name()));
		}
		return titleDropDownList;
	}

//	private Customer createNewCustomer(CustomerModel cModel) {
//		
//		Customer newCustomer = null;
//		
//		Customer existingCustomer = customerService.getCustomerByUserName(cModel.getUserName());
//		if (existingCustomer != null) {
//			
//		} else {
//			// UserName is unique.
//			Date birthDate = null;
//			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
//			try {
//				birthDate = format.parse(cModel.getDob());				
//				newCustomer = customerService.createCustomer(cModel.getFirstName(), cModel.getLastName(),
//						cModel.getUserName(), birthDate);
//			} catch (ParseException pe) {
//				
//			}			
//		}
//		
//		return newCustomer;
//	}
	

}