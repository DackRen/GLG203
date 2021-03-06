package com.yaps.petstore.common.dto;

public class CustomerDTO implements DataTransfertObject{
	public CustomerDTO() {
	}
	
	public CustomerDTO(String id, String firstname, String lastname) {
		this._id=id;
		this._firstname=firstname;
		this._lastname=lastname;
	}
	
	@Override
	public String toString() {
		return "CustomerDTO [_id=" + _id + ", _firstname=" + _firstname + ", _lastname=" + _lastname + ", _telephone="
				+ _telephone + ", _street1=" + _street1 + ", _street2=" + _street2 + ", _city=" + _city + ", _state="
				+ _state + ", _zipcode=" + _zipcode + ", _country=" + _country + "]";
	}
	
	public String getId() {
		return _id;
	}
	public void setId(String id) {
		_id = id;
	}
	public String getFirstname() {
		return _firstname;
	}
	public void setFirstname(String firstname) {
		_firstname = firstname;
	}
	public String getLastname() {
		return _lastname;
	}
	public void setLastname(String lastname) {
		_lastname = lastname;
	}
	public String getTelephone() {
		return _telephone;
	}
	public void setTelephone(String telephone) {
		_telephone = telephone;
	}
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
	
	private String _id;
	private String _firstname;
    private String _lastname;
    private String _telephone;
    private String _street1;
    private String _street2;
    private String _city;
    private String _state;
    private String _zipcode;
    private String _country;
    
}
