package joe.spring.springapp.services.impl;

import java.util.List;

import joe.spring.springapp.data.domain.Account;
import joe.spring.springapp.data.domain.Account.AccountType;
import joe.spring.springapp.data.domain.Customer;
import joe.spring.springapp.data.jpa.repository.AccountRepository;
import joe.spring.springapp.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {


	@Autowired
	private AccountRepository accountRepo;

	public AccountServiceImpl() {
	}

	@Override
	public Account createAccount(AccountType accountType, String accountNumber, Customer customer) {

		Account newAccount = null;

		if (customer != null && customer.getId() != null) {
			newAccount = accountRepo.save(new Account(accountType, accountNumber, customer));
		} else {
			throw new IllegalStateException("Missing method parameter.");
		}
		
		return newAccount;
	}

	@Override
	public void removeAccount(Long accountId) {
		if (accountId != null) {
			accountRepo.delete(accountId);		
		} else {
			throw new IllegalStateException("Missing method parameter.");
		}
	}

	@Override
	public void removeAllAccounts() {
		accountRepo.deleteAll();			
	}

	@Override
	public Account updateAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccountById(Long accountId) {

		Account account = null;

		if (accountId != null) {
			account = accountRepo.findOne(accountId);	
		} else {
			throw new IllegalStateException("Missing method parameter.");
		}
		
		return account;
	}

	@Override
	public List<Account> getAccountsByCustomer(Customer customer) {
		List<Account> accountList = null;

		if (customer != null) {
			accountList = accountRepo.findByCustomer(customer);	
		} else {
			throw new IllegalStateException("Missing method parameter.");
		}
		
		return accountList;
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountRepo.findAll();
	}

	@Override
	public List<Account> getFreeAccounts() {
		return accountRepo.getFreeAccounts();
	}

	@Override
	public List<Account> getPremiumAccounts() {
		return accountRepo.getPremiumAccounts();
	}

	@Override
	public List<Account> getAccountsByType(AccountType at) {
		return accountRepo.getAccountsByType(at);
	}


}
