package com.yaps.petstore.common.dto;

import java.util.Collection;
import java.util.Date;

import com.yaps.petstore.server.domain.customer.Customer;

public class OrderDTO implements DataTransfertObject{
	
	public OrderDTO() {
	}
	
	public OrderDTO(String firstname, String lastname, String street1, String city, String zipcode, String country) {
		this._firstname=firstname;
		this._lastname=lastname;
		this._street1=street1;
		this._city=city;
		this._zipcode=zipcode;
		this._country=country;
	}
	
	@Override
	public String toString() {
		return "OrderDTO [_id=" + _id + ", _orderDate=" + _orderDate + ", _firstname=" + _firstname + ", _lastname="
				+ _lastname + ", _street1=" + _street1 + ", _street2=" + _street2 + ", _city=" + _city + ", _state="
				+ _state + ", _zipcode=" + _zipcode + ", _country=" + _country + ", _creditCardNumber="
				+ _creditCardNumber + ", _creditCardType=" + _creditCardType + ", _creditCardExpiryDate="
				+ _creditCardExpiryDate + "]";
	}
	
	public String getId() {
		return _id;
	}
	public void setId(String id) {
		_id = id;
	}
	public Date getOrderDate() {
		return _orderDate;
	}
	public void setOrderDate(Date orderDate) {
		_orderDate = orderDate;
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
	public String getCreditCardNumber() {
		return _creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		_creditCardNumber = creditCardNumber;
	}
	public String getCreditCardType() {
		return _creditCardType;
	}
	public void setCreditCardType(String creditCardType) {
		_creditCardType = creditCardType;
	}
	public String getCreditCardExpiryDate() {
		return _creditCardExpiryDate;
	}
	public void setCreditCardExpiryDate(String creditCardExpiryDate) {
		_creditCardExpiryDate = creditCardExpiryDate;
	}
	public Collection getOrderLines() {
		return _orderLines;
	}
	public void setOrderLines(Collection orderLines) {
		_orderLines = orderLines;
	}
    public String getCustomerId() {
		return _customerId;
	}
	public void setCustomerId(String customerId) {
		_customerId = customerId;
	}
//	public String getCustomerName() {
//		return _customerName;
//	}
//	public void setCustomerName(String customerName) {
//		_customerName = customerName;
//	}
	
	private String _id;
    private Date _orderDate;
    private String _firstname;
    private String _lastname;
    private String _street1;
    private String _street2;
    private String _city;
    private String _state;
    private String _zipcode;
    private String _country;
    private String _creditCardNumber;
    private String _creditCardType;
    private String _creditCardExpiryDate;
    private String _customerId;
    //private String _customerName;
	private Collection _orderLines;


}
