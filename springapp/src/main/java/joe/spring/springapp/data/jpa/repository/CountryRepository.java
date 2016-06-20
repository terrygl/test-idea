package joe.spring.springapp.data.jpa.repository;

import joe.spring.springapp.data.domain.Account;
import joe.spring.springapp.data.domain.Customer;
import joe.spring.springapp.data.reference.Country;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to manage {@link Account} instances.
 * 
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

	/**
	 * Returns all accounts belonging to the given {@link Customer}.
	 * 
	 * @param customer
	 * @return
	 */	
	@Cacheable("joe.spring.springapp.data.countries")	
	Customer findById(Long id);

	@Cacheable("joe.spring.springapp.data.countries")	
	Country findByCode(String code);
	
}