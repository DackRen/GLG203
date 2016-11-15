package com.yaps.petstore.domain.order;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

import com.yaps.petstore.domain.DomainObject;
import com.yaps.petstore.domain.customer.Customer;
import com.yaps.petstore.domain.orderline.OrderLine;
import com.yaps.petstore.exception.CheckException;


public class Order extends DomainObject implements Serializable{
	
	// ======================================
    // =            Constructors            =
    // ======================================
	
	public Order() {
	}
	
	public Order(final String id) {
		this._id=id;
	}

	public Order(final String id, final java.util.Date orderDate,final String firstname,final String lastname,final String street1,final String street2,
			final String city,final String state,final String zipcode,final String country ,final String creditCardNumber,final String creditCardType, 
			final String creditCardExpiryDate,final Customer customer) {
		this._id = id;
		this._orderDate = orderDate;
		this._firstname = firstname;
		this._lastname = lastname;
		this._street1 = street1;
		this._street2 = street2;
		this._city = city;
		this._state = state;
		this._zipcode = zipcode;
		this._country = country;
		this._creditCardNumber = creditCardNumber;
		this._creditCardType = creditCardType;
		this._creditCardExpiryDate = creditCardExpiryDate;
		this._customer_fk = customer;
		
	}
	
	//for CreateOrderFrame.buttonCreateActionPerformed?
	public Order(final String firstName, final String lastName, final String street1, final String city, final String zipcode,final String country,final Customer customer) {
		this._firstname = firstName;
		this._lastname = lastName;
		this._street1 = street1;
		this._city = city;
		this._zipcode = zipcode;
		this._country = country;
		this._customer_fk = customer;
	}
	//test
	public Order(final String id, final java.util.Date date, final String firstname, final String lastname, final String street1, final String city,
			final String zip, final String country, final Customer customer) {
		this._id=id;
		this._orderDate=date;
		this._firstname = firstname;
		this._lastname = lastname;
		this._street1 = street1;
		this._city = city;
		this._zipcode = zip;
		this._country = country;
		this._customer_fk = customer;
	}
	
    // ======================================
    // =           Business methods         =
    // ======================================

	public void checkData() throws CheckException {
		if (_id == null || "".equals(_id))
			throw new CheckException("Invalid ID");
		if (_firstname == null || "".equals(_firstname))
			throw new CheckException("Invalid firstname");
		if (_lastname== null || "".equals(_lastname))
			throw new CheckException("Invalid lastname");
		if (_street1 == null || "".equals(_street1))
			throw new CheckException("Invalid street1");
		if (_country == null || "".equals(_country))
			throw new CheckException("Invalid country");
		if (_city== null || "".equals(_city))
			throw new CheckException("Invalid city");
		if (_zipcode== null || "".equals(_zipcode))
			throw new CheckException("Invalid zipcode");		
		if (_customer_fk == null || _customer_fk.getId() == null || "".equals(_customer_fk.getId()))
			throw new CheckException("Invalid product");
	}


	
	public String toString() {
        final StringBuffer buf = new StringBuffer();
//        buf.append("\n\tItem {");
        buf.append("\n\t\tId=").append(_id);
//        buf.append("\n\t\tName=").append(_name);
//        buf.append("\n\t\tUnit Cost=").append(_unitCost);
//        buf.append("\n\t\tProduct Id=").append(_product.getId());
//        buf.append("\n\t\tProduct Name=").append(_product.getName());
        buf.append("\n\t}");
        return buf.toString();
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
	
	public java.util.Date getOrderDate() {
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
	public Collection<OrderLine> getOrderLines() {
		return _orderLines;
	}
	public void setOrderLines(Collection<OrderLine> orderLines) {
		_orderLines = orderLines;
	}
    public Customer getCustomer() {
		return _customer_fk;
	}
	public void setCustomer(Customer customer) {
		_customer_fk = customer;
	}
	
    // ======================================
    // =             Attributes             =
    // ======================================
	
	private java.util.Date _orderDate;
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
    private Customer _customer_fk;
	private Collection<OrderLine> _orderLines;

}
