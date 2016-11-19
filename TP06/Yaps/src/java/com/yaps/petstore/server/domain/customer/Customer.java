package com.yaps.petstore.server.domain.customer;

import java.io.Serializable;


import com.yaps.petstore.common.dto.AddressDTO;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.server.domain.Address;
import com.yaps.petstore.server.domain.CreditCard;
import com.yaps.petstore.server.domain.DomainObject;

public class Customer extends DomainObject implements Serializable{
	private String _firstname;
	private String _lastname;
	private String _telephone;
	private String _email;
	
	private final Address _address  = new Address();
    private final CreditCard _creditCard = new CreditCard();
	
	public Customer(String id, String firstName, String lastName) {
		this.setId(id);
		this._firstname=firstName;
		this._lastname=lastName;
		
	}
	public Customer(String id) {
		this.setId(id);
	}

	public String getFirstname() {
		return _firstname;
	}
	public void setFirstname(String firstName) {
		_firstname = firstName;
	}
	public String getLastname() {
		return _lastname;
	}
	public void setLastname(String lastName) {
		_lastname = lastName;
	}
	public String getTelephone() {
		return _telephone;
	}
	public void setTelephone(String telephone) {
		_telephone = telephone;
	}
	public String getEmail() {
		return _email;
	}
	public void setEmail(String email) {
		_email = email;
	}

    public String getStreet1() {
        return _address.getStreet1();
    }

    public void setStreet1(final String street1) {
        _address.setStreet1(street1);
    }

    public String getStreet2() {
        return _address.getStreet2();
    }

    public void setStreet2(final String street2) {
        _address.setStreet2(street2);
    }

    public String getCity() {
        return _address.getCity();
    }

    public void setCity(final String city) {
        _address.setCity(city);
    }

    public String getState() {
        return _address.getState();
    }

    public void setState(final String state) {
        _address.setState(state);
    }

    public String getZipcode() {
        return _address.getZipcode();
    }

    public void setZipcode(final String zipcode) {
        _address.setZipcode(zipcode);
    }

    public String getCountry() {
        return _address.getCountry();
    }

    public void setCountry(final String country) {
        _address.setCountry(country);
    }

    public String getCreditCardNumber() {
        return _creditCard.getCreditCardNumber();
    }

    public void setCreditCardNumber(final String creditCardNumber) {
        _creditCard.setCreditCardNumber(creditCardNumber);
    }

    public String getCreditCardType() {
        return _creditCard.getCreditCardType();
    }

    public void setCreditCardType(final String creditCardType) {
        _creditCard.setCreditCardType(creditCardType);
    }

    public String getCreditCardExpiryDate() {
        return _creditCard.getCreditCardExpiryDate();
    }

    public void setCreditCardExpiryDate(final String creditCardExpiryDate) {
        _creditCard.setCreditCardExpiryDate(creditCardExpiryDate);
    }
	public void checkData() throws CheckException {
		if (_firstname == null || "".equals(_firstname))
			throw new CheckException("Invalid customer first name");
		if (_lastname == null || "".equals(_lastname))
			throw new CheckException("Invalid customer last name");
		
	}
	public String toString () {
        final StringBuffer buf = new StringBuffer (); 
       buf.append ( "Customer {" ); 
       buf.append ( "firstname =" ) .append (getFirstname ()); 
       buf.append ( "email =" ) .append (getEmail ()); 
       buf.append ( "country =" ) .append (getCountry ()); 
       buf.append ( '}'); return buf.toString (); 

	}
}
