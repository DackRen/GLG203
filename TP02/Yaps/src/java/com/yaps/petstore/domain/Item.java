package com.yaps.petstore.domain;

import java.io.Serializable;

import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.ObjectNotFoundException;

public class Item implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//constructors
	public Item() {
	}

	public Item(String id, String name, double unitCost, Product product) {
		this._id=id;
		this._name=name;
		this._unitCost=unitCost;
		this._product=product;
	}

	//++++++++++++++++++++++++++++++++++++++++++++++++++++++
	@Override
	public String toString(){
        final StringBuffer buf = new StringBuffer();
        buf.append("\n\tCustomer {");
        buf.append("\n\t\tId=").append(_id);
        buf.append("\n\t\tName=").append(_name);
        buf.append("\n\t\t_UnitCost=").append(_unitCost);
        buf.append("\n\t}");
        return buf.toString();
	}
	
	public void checkData() throws CheckException{
		if(this._id==null||this._name==null)
			throw new CheckException("Object with null values should not be created");
		if(this._id.equals("")||this._name.equals(""))
			throw new CheckException("Object with empty values should not be created");
		if(this._product==null)
			throw new CheckException("Object with null object linked should not be created");
		if(this._product.getName()==null)
			throw new CheckException("Object with an empty object linked should not be created");
		
	}
	
	// getter&setter
	
	public String getId() throws ObjectNotFoundException{
		return this._id;
	}
	
	public String getName() {
		return this._name;
	}

	public double getUnitCost() {

		return this._unitCost;
	}
	
	public void setId(String id) {
		this._id=id;
		
	}
	
	public void setName(String name) {
		this._name=name;
		
	}
	
	public void setUnitCost(double unitCost) {
		this._unitCost=unitCost;
		
	}
	
	public void setProduct(Product product) {
		this._product=product;	
	}
	
	public Product getProduct() {
		return this._product;
	}
	
	//++++++++++++++++++++++++++++++++++++++
	private Product _product;
	
	private String _id;
	private String _name;
	private double _unitCost;

}
