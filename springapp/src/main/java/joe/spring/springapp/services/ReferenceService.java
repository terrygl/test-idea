package joe.spring.springapp.services;

import java.util.List;

import joe.spring.springapp.data.reference.Country;
import joe.spring.springapp.data.reference.State;
import joe.spring.springapp.data.reference.Title;

/**
 * An administrative service used to manage customers and accounts.
 * 
 * @author joeontech
 *
 */
public interface ReferenceService {

	public List<Country> getAllCountries();
	public Country getCountryById(final Long countryId);
	public Country getCountryByCode(String code);
	public List<State> getAllStates();	
	public State getStateById(final Long stateId);
	public State getStateByCode(final String code);
	public List<State> getStatesByCountry(Country country);	
	public List<Title> getAllTitles();	
}


