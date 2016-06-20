package joe.spring.springapp.data.jpa.repository;

import java.util.List;

import joe.spring.springapp.data.domain.Account;
import joe.spring.springapp.data.domain.Customer;
import joe.spring.springapp.data.domain.Account.AccountType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository to manage {@link Account} instances.
 * 
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	/**
	 * Returns all accounts belonging to the given {@link Customer}.
	 * 
	 * @param customer
	 * @return
	 */
	List<Account> findByCustomer(Customer customer);
	
	@Query("select a from Account a where a.accountType = 'PREMIUM'")
	List<Account> getPremiumAccounts();

	@Query("select a from Account a where a.accountType = 'FREE'")
	List<Account> getFreeAccounts();

	@Query("select a from Account a where a.accountType = ?1")
	List<Account> getAccountsByType(AccountType accountType);
	
}