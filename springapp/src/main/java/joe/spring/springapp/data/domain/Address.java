package joe.spring.springapp.data.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import joe.spring.springapp.data.reference.State;

@Entity(name="ADDRESS")
public class Address {

	public enum AddressType {
		HOME, BILLING, BUSINESS, OTHER;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="springSeq")
    @SequenceGenerator( name = "springSeq", sequenceName = "SPRING_SEQ")
	private Long id;

	private String primaryAddressLine;

	private String secondaryAddressLine;

	private String cityName;

	@ManyToOne
	@JoinColumn(name = "STATE_ID")
	private State state;

	private String zipCode;

	@Enumerated(EnumType.STRING)
	private AddressType addressType;

	@ManyToOne()
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	@SuppressWarnings("unused")
	private Address() {

	}

	public Address(final String primaryAddressLine,
			final String secondaryAddressLine, final String cityName,
			final State state, final String zipCode,
			final AddressType addressType) {
		this(primaryAddressLine, secondaryAddressLine, cityName, state, zipCode, null, addressType);
	}

	public Address(final String primaryAddressLine,
			final String secondaryAddressLine, final String cityName,
			final State state, final String zipCode, final Customer customer,
			final AddressType addressType) {
		super();
		this.primaryAddressLine = primaryAddressLine;
		this.secondaryAddressLine = secondaryAddressLine;
		this.cityName = cityName;
		this.state = state;
		this.zipCode = zipCode;
		this.customer = customer;
		this.addressType = addressType;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrimaryAddressLine() {
		return primaryAddressLine;
	}

	public void setPrimaryAddressLine(String primaryAddressLine) {
		this.primaryAddressLine = primaryAddressLine;
	}

	public String getSecondaryAddressLine() {
		return secondaryAddressLine;
	}

	public void setSecondaryAddressLine(String secondaryAddressLine) {
		this.secondaryAddressLine = secondaryAddressLine;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", addressType="
				+ addressType + ", primaryAddressLine="
				+ primaryAddressLine + ", secondaryAddressLine="
				+ secondaryAddressLine + ", cityName=" + cityName + ", state="
				+ state + ", zipCode=" + zipCode + ", customer="
				+ customer + "]";
	}

}