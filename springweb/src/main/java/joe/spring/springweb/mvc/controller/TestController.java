package joe.spring.springweb.mvc.controller;

import java.util.ArrayList;

import joe.spring.springapp.data.domain.Customer;
import joe.spring.springapp.services.CustomerService;
import joe.spring.springweb.mvc.data.CustomerDto;
import joe.spring.springweb.mvc.data.DtoListWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller used to test Content Negotiation in Spring MVC. A contentNegotiationManager bean has been
 * configured to allow both JSON and XML data. The default is JSON, but the format can be selected
 * using either .xml or .json at the end of the request. 
 */
@Controller
public class TestController {

	@Autowired
	protected CustomerService customerService;
	
	protected final static Logger log = LoggerFactory
			.getLogger(TestController.class);

	public TestController() {

	}

	// Displays a list of all customers.

	@RequestMapping(value = "/getCustomers",produces={"application/xml", "application/json"}, method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody DtoListWrapper<CustomerDto> getAllCustomers() {
		log.info("Fetching a list of all customers.");
		ArrayList<CustomerDto> customerDtoList = new ArrayList<CustomerDto>();
		ArrayList<Customer> customerList = (ArrayList<Customer>) customerService
				.getAllCustomers();		
		for (Customer c : customerList) {
			customerDtoList.add(new CustomerDto(c.getFirstName(), c.getLastName(), c.getUserName(), c.getBirthDate()));
		}
		return new DtoListWrapper<CustomerDto>(customerDtoList);
	}

}