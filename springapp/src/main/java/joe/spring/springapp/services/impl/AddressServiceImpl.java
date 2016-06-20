package joe.spring.springapp.services.impl;

import java.util.List;

import joe.spring.springapp.data.domain.Address;
import joe.spring.springapp.data.domain.Customer;
import joe.spring.springapp.data.domain.Address.AddressType;
import joe.spring.springapp.data.jpa.repository.AddressRepository;
import joe.spring.springapp.data.reference.State;
import joe.spring.springapp.services.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepo;

	public AddressServiceImpl() {
	}

	@Override
	public Address createAddress(final String primaryAddressLine,
			final String secondaryAddressLine, final String cityName, final State state,
			final String zipCode, final AddressType addressType) {
		Address newAddress = null;

		if (state != null && state.getId() != null) {
			Address address = new Address(primaryAddressLine, secondaryAddressLine,
					cityName, state, zipCode, addressType);
			newAddress = addressRepo.save(address);
		} else {
			throw new IllegalStateException("Missing method parameter.");
		}
		
		return newAddress;
	}

	@Override
	public Address createAddress(final String primaryAddressLine,
			final String secondaryAddressLine, final String cityName, final State state,
			final String zipCode, final Customer customer, final AddressType addressType) {
		Address newAddress = null;

		if (state != null && state.getId() != null) {
			Address address = new Address(primaryAddressLine, secondaryAddressLine,
					cityName, state, zipCode, customer, addressType);
			newAddress = addressRepo.save(address);
		} else {
			throw new IllegalStateException("Missing method parameter.");
		}
		
		return newAddress;
	}


	@Override
	public Address getAddressById(Long addressId) {
		Address address = null;

		if (addressId != null) {
			address = addressRepo.findOne(addressId);	
		} else {
			throw new IllegalStateException("Missing method parameter.");
		}
		
		return address;
	}

	@Override
	public List<Address> getAllAddresses() {
		return addressRepo.findAll();
	}

	@Override
	public void removeAllAddresses() {
		addressRepo.deleteAll();			
		
	}

}
