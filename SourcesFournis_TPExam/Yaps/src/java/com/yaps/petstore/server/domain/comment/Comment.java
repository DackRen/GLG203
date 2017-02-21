package com.yaps.petstore.server.domain.comment;

import java.io.Serializable;

import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.server.domain.DomainObject;
import com.yaps.petstore.server.domain.customer.Customer;

public class Comment extends DomainObject implements Serializable {
	// ======================================
    // =             Attributes             =
    // ======================================
    String _title;
	String _text;
    Customer _customer; // author of the Comment
    java.util.Date _date; // date of the Comment
    
 // ======================================
    // =            Constructors            =
    // ======================================
    public Comment() {
    }

    public Comment(final String id,String title, String text, Customer customer, java.util.Date date) {
        setId(id);
        this._title = title;
        this._text = text;
        this._customer = customer;
        this._date = date;
    }
    
    public Comment(String title, String text) {
		this._title=title;
		this._text=text;
	}



	// ======================================
    // =         Getters and Setters        =
    // ======================================
    public String getTitle() {
		return _title;
	}
	public void setTitle(String title) {
		_title = title;
	}
	public String getText() {
		return _text;
	}
	public void setText(String text) {
		_text = text;
	}
	public Customer getCustomer() {
		return _customer;
	}
	public void setCustomer(Customer customer) {
		_customer = customer;
	}
	public java.util.Date getDate() {
		return _date;
	}
	public void setDate(java.util.Date date) {
		_date = date;
	}

	public void checkData() throws CheckException{
		if(_title==null||_title.equals(""))
			throw new CheckException("title error");
		if(_text==null||_text.equals(""))
			throw new CheckException("text error");
	}

	public boolean checkTitle() {
		if(this._title.length()<10||this._title.length()>65)
			return false;
		if(!(this._title.endsWith(".")||this._title.endsWith("!")||this._title.endsWith("?")))
			return false;
		if(!((this._title.charAt(0)>'A'&&this._title.charAt(0)<'Z')||(this._title.charAt(0)>'0'&&this._title.charAt(0)<'9'))){
			return false;	
		}
		return true;
	}
}
