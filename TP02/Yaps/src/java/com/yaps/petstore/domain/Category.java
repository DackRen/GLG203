package com.yaps.petstore.domain;

import java.io.Serializable;
import java.util.HashMap;

import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.ObjectNotFoundException;

public class Category implements Serializable{
	
	private static final long serialVersionUID = 1L;

	//constructors
	public Category() {
	}
	
	public Category(String id) {
		this._id=id;
	}

	public Category(String id, String name, String description) {
		this._id=id;
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
	
	public String getId() throws ObjectNotFoundException{
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
	
	public HashMap<Product, String> get_productsMap() {
		return _productsMap;
	}
	
	//+++++++++++++++++++
	private String _id;
	private String _name;
	private String _description;
	
	private HashMap<Product, String> _productsMap;

}
