package com.yaps.petstore.server.domain;

import com.yaps.petstore.common.exception.CheckException;

public class Address extends DomainObject {
	
	
	private String _street1;
    private String _street2;
    private String _city;
    private String _state;
    private String _zipcode;
    private String _country;
    
    
    
	public String getStreet1() {
		return _street1;
	}
	public void setStreet1(String street1) {
		_street1 = street1;
	}
	public String getStreet2() {
		return _street2;
	}
	public void setStreet2(String street2) {
		_street2 = street2;
	}
	public String getCity() {
		return _city;
	}
	public void setCity(String city) {
		_city = city;
	}
	public String getState() {
		return _state;
	}
	public void setState(String state) {
		_state = state;
	}
	public String getZipcode() {
		return _zipcode;
	}
	public void setZipcode(String zipcode) {
		_zipcode = zipcode;
	}
	public String getCountry() {
		return _country;
	}
	public void setCountry(String country) {
		_country = country;
	}
	
	public void checkData() throws CheckException {
		if(_street1==null||_city==null||_state==null||_zipcode==null||_country==null)
			throw new CheckException("invalued ");
		if(_street1.equals("")||_city.equals("")||_state.equals("")||_zipcode.equals("")||_country.equals(""))
			throw new CheckException("invalued ");
		
	}
    

}
