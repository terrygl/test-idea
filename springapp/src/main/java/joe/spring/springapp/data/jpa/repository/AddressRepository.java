package joe.spring.springapp.data.jpa.repository;

import java.util.List;

import joe.spring.springapp.data.domain.Account;
import joe.spring.springapp.data.domain.Address;
import joe.spring.springapp.data.domain.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to manage {@link Account} instances.
 * 
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	/**
	 * Returns all accounts belonging to the given {@link Customer}.
	 * 
	 * @param customer
	 * @return
	 */
	List<Address> findByAddressType(String addressType);
	
}