package joe.spring.springapp.data.reference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class State {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="springSeq")
    @SequenceGenerator( name = "springSeq", sequenceName = "SPRING_SEQ")
	private Long id;

	private String name;
	private String code;

	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID")
	private Country country;

	
	@SuppressWarnings("unused")
	private State() {
		
	}

	public State(final String name, final String code, Country c) {
		this.name = name;
		this.code = code;
		this.country = c;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}


	@Override
	public String toString() {
		return "State [id=" + id + ", name=" + name + ", code=" + code + ", country=" + country + "]";
	}

}