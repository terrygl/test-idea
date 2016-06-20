package joe.spring.springapp.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import joe.spring.springapp.data.domain.Address;
import joe.spring.springapp.data.domain.Customer;
import joe.spring.springapp.data.jpa.repository.CustomerRepository;
import joe.spring.springapp.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	public CustomerServiceImpl() {
	}

	@Override
	public Customer createCustomer(String firstName, String lastName,
			String userName, Date birthDate) {

		return createCustomer(firstName, lastName, userName, birthDate, null);
	}

	@Override
	public Customer createCustomer(String firstName, String lastName,
			String userName, Date birthDate, HashSet<Address> addresses) {

		Customer newCustomer = null;

		if (firstName != null && !firstName.isEmpty() && lastName != null
				&& !lastName.isEmpty()) {
			newCustomer = customerRepo.save(new Customer(firstName, lastName,
					userName, birthDate, addresses));
		} else {
			throw new IllegalStateException("Missing method parameter.");
		}
		return newCustomer;
	}

	@Override
	public Customer updateCustomer(Customer c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getCustomersByLastName(String lastName) {
		List<Customer> customerList = null;
		if (lastName != null && !lastName.isEmpty()) {
			customerList = customerRepo.findByLastName(lastName);
		} else {
			throw new IllegalStateException("Missing method parameter.");
		}
		return customerList;
	}

	@Override
	public Customer getCustomerById(Long customerId) {

		Customer c = null;
		if (customerId != null) {
			c = customerRepo.findOne(customerId);
		} else {
			throw new IllegalStateException("Missing method parameter.");
		}
		return c;
	}

	@Override
	public Customer getCustomerByUserName(final String userName) {
		Customer c = null;
		if (userName != null && !userName.trim().isEmpty()) {
			c = customerRepo.findByUserName(userName);
		} else {
			throw new IllegalStateException("Missing method parameter.");
		}
		return c;
	}

	@Override
	public void removeCustomer(Long customerId) {

		if (customerId != null) {
			customerRepo.delete(customerId);
		} else {
			throw new IllegalStateException("Missing method parameter.");
		}

	}

	@Override
	public void removeAllCustomers() {
		customerRepo.deleteAll();
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customerList = customerRepo.findAll();
		return customerList;
	}

	@Override
	public List<Customer> searchCustomers(final String searchTerm) {
		
		List<Customer> customerList = new ArrayList<Customer>();
		if (searchTerm != null && !searchTerm.trim().isEmpty()) {
			customerList = customerRepo.searchCustomers(searchTerm);
		} else {
			customerList = customerRepo.findAll();
		}

		return customerList;		
	}
	
}
