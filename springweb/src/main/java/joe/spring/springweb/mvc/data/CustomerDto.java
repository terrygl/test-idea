package joe.spring.springweb.mvc.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "customer")
public class CustomerDto {

	private String firstName;

	private String lastName;
	
	private String userName;
	
	private Date dob;

	public CustomerDto() {
		super();
	}

	public CustomerDto(String firstName, String lastName,
			String userName, Date dob) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.dob = dob;
	}

	@XmlElement
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@XmlElement
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@XmlElement
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@XmlElement
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}


	@Override
	public String toString() {
		return "CustomerDto [firstName=" + firstName
				+ ", lastName=" + lastName + ", userName=" + userName
				+ ", dob=" + dob + "]";
	}
	
}
