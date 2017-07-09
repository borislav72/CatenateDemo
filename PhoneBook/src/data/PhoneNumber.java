package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Entity implementation class for table: phone_number
 * 
 * @author Administrator
 *
 */
@Entity
@XmlRootElement
@NamedQuery(name = PhoneNumber.FIND_ALL, query = "SELECT p FROM PhoneNumber p")
@Table(name = "phone_number")
public class PhoneNumber {

	public static final String FIND_ALL = "PhoneNumber.FIND_ALL";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")	
	private Long id;

	@ManyToOne
	@JoinColumn(name="COUNTRY_CODE_ID")
	private CountryCode countryCode;
	
	@Column(name = "NAME")	
	private String name;

	@Column(name = "PHONE")	
	private String phone;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CountryCode getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
