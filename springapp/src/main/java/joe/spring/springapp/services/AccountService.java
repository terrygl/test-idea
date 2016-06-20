package joe.spring.springapp.services;

import java.util.List;

import joe.spring.springapp.data.domain.Account;
import joe.spring.springapp.data.domain.Account.AccountType;
import joe.spring.springapp.data.domain.Customer;

/**
 * An administrative service used to manage customers and accounts.
 * 
 * @author joeontech
 *
 */
public interface AccountService {
	
	public Account createAccount(AccountType accountType, String accountNumber, Customer customer);
	public Account updateAccount(final Account account);
	public Account getAccountById(final Long accountId);
	public List<Account> getAccountsByCustomer(final Customer customer);
	public List<Account> getAllAccounts();
	public void removeAccount(final Long accountId);
	public void removeAllAccounts();
	public List<Account> getFreeAccounts();
	public List<Account> getPremiumAccounts();
	public List<Account> getAccountsByType(AccountType at);
	
}


