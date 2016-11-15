package com.yaps.petstore.domain;


import java.io.Serializable;
import java.util.HashMap;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.ObjectNotFoundException;

public class Product implements Serializable{

	private static final long serialVersionUID = 1L;

	//constructors
	public Product() {
	}
	
	public Product(String id, String name, String description, Category category) {
		this._id=id;
		this._name=name;
		this._description=description;
		this._category=category;

	}

	//++++++++++++++++++++++++
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
		if(this._id==null||this._name==null||this._description==null)
			throw new CheckException("Object with null values should not be created");
		if(this._id.equals("")||this._name.equals("")||this._description.equals(""))
			throw new CheckException("Object with empty values should not be created");
		if(this._category==null)
			throw new CheckException("Object with null object linked should not be created");
		if(this._category.getName()==null)
			throw new CheckException("Object with an empty object linked should not be created");
	}

	// getter&setter
	
	public void setCategory(Category category) {
		this._category = category;
	}
	public Category getCategory() {
		return this._category;
	}
	
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
	
	public HashMap<Item, String> get_itemsMap() {
		return _itemsMap;
	}

	//++++++++++++++++++++++++++++
	private Category _category;
	
	private String _id;
	private String _name;
	private String _description;

	private HashMap<Item,String> _itemsMap;


}

