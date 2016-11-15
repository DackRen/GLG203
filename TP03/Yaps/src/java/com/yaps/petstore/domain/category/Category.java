package com.yaps.petstore.domain.category;

import java.io.Serializable;
import java.util.HashMap;

import com.yaps.petstore.domain.DomainObject;
import com.yaps.petstore.domain.product.Product;
import com.yaps.petstore.exception.CheckException;

public class Category extends DomainObject implements Serializable{
	
	private static final long serialVersionUID = 1L;

	//constructors
	public Category() {
	}
	
	public Category(String id) {
		_id=id;
	}

	public Category(String id, String name, String description) {
		_id=id;
		this._name=name;
		this._description=description;
	}
	
	//++++++++++++
	@Override
	public String toString(){
        final StringBuffer buf = new StringBuffer();
        buf.append("\n\tCustomer {");
        buf.append("\n\t\tId=").append(_id);
        buf.append("\n\t\tName=").append(_name);
        buf.append("\n\t\tDescription=").append(_description);
        buf.append("\n\t}");
        return buf.toString();
	}
	
	public void checkData() throws CheckException{
		if(this._name==null||this._description==null)
			throw new CheckException("Updating an object with null values should break");
		if(this._name.equals("")||this._description.equals(""))
			throw new CheckException("Updating an object with empty values should break");
	}

	// getter&setter
	
	public String getId(){
		return this._id;
	}
	
	public String getName() {
		return this._name;
	}

	public String getDescription() {
		return this._description;
	}

	public void setId(String id) {
		this._id=id;
	}
	
	public void setName(String name) {
		this._name=name;		
	}

	public void setDescription(String description) {
		this._description=description;	
	}
	
	//+++++++++++++++++++
	private String _name;
	private String _description;


}
