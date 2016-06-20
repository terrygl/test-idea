package joe.spring.springapp.services;

import java.util.List;

import joe.spring.springapp.data.domain.Address;
import joe.spring.springapp.data.domain.Customer;
import joe.spring.springapp.data.domain.Address.AddressType;
import joe.spring.springapp.data.reference.State;

/**
 * An service used to manage addresses.
 * 
 * @author joeontech
 * 
 */
public interface AddressService {

	public Address createAddress(final String primaryAddressLine,
			final String secondaryAddressLine, final String cityName,
			final State state, final String zipCode,
			final AddressType addressType);

	public Address createAddress(final String primaryAddressLine,
			final String secondaryAddressLine, final String cityName,
			final State state, final String zipCode,
			final Customer customer,
			final AddressType addressType);

	public Address getAddressById(final Long addressId);

	public List<Address> getAllAddresses();

	public void removeAllAddresses();

//	public List<Address> getAddressesByType(AddressType at);

}
