package joe.spring.springapp.data.jpa.repository;

import java.util.List;

import joe.spring.springapp.data.domain.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository to manage {@link Customer} instances.
 * 
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByLastName(String lastName);
	Customer findByUserName(String userName);	
	
	@Query("select c from Customer c where c.firstName like %:searchTerm% OR c.lastName like %:searchTerm% OR c.userName like %:searchTerm%")
	List<Customer> searchCustomers(@Param("searchTerm") String searchTerm);
	
	
}