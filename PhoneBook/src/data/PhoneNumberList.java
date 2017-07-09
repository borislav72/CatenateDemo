package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * List of known PhoneNumbers that can transform into xml or json.
 * 
 * @author Borislav
 *
 */
@XmlRootElement
@XmlSeeAlso(PhoneNumber.class)
public class PhoneNumberList extends ArrayList<PhoneNumber> {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public PhoneNumberList() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param c
	 *            collection of initial PhoneNumber.
	 */
	public PhoneNumberList(Collection<? extends PhoneNumber> c) {
		super(c);
	}

	/**
	 * Get list of PhoneNumber.
	 * 
	 * @return PhoneNumber list
	 */
	@XmlElement(name = "phoneNumbers")
	public List<PhoneNumber> getPhoneNumbers() {
		return this;
	}

	/**
	 * Add a list of PhoneNumber.
	 * 
	 * @param phoneNumbers
	 *            list to add
	 */
	public void addPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.addAll(phoneNumbers);
	}

}
