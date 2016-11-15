package com.yaps.petstore.domain.orderline;

import java.io.Serializable;

import com.yaps.petstore.domain.DomainObject;
import com.yaps.petstore.domain.item.Item;
import com.yaps.petstore.domain.order.Order;
import com.yaps.petstore.exception.CheckException;

public class OrderLine extends DomainObject implements Serializable{

	// ======================================
    // =            Constructors            =
    // ======================================	
	public OrderLine(String id) {
		this._id=id;
	}
	
	public OrderLine(String id, int quantity, double unitCost, Order order, Item item) {
		this._id=id;
		this._quantity=quantity;
		this._unitCost=unitCost;
		this._order_fk=order;
		this._item_fk=item;
	}
	
	//for test
	public OrderLine(int quantity, double unitCost, Order order, Item item) {
		this._quantity=quantity;
		this._unitCost=unitCost;
		this._order_fk=order;
		this._item_fk=item;
	}
	
    // ======================================
    // =           Business methods         =
    // ======================================
	
	public void checkData() throws CheckException{
		if(_id==null||_id.equals(""))
			throw new CheckException("Invalid id");
		if(_quantity<=0){
			throw new CheckException("Invalid quantity");
		}
		if(_unitCost<=0){
			throw new CheckException("Invalid unitCost");
		}
		if(_order_fk==null||_order_fk.getId()==null||"".equals(_order_fk.getId())){
			throw new CheckException("Invalid order");
		}
		if(_item_fk==null||_item_fk.getId()==null||"".equals(_item_fk.getId())){
			throw new CheckException("Invalid _item_fk");
		}
		
	
		
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

	public int getQuantity() {
		return _quantity;
	}
	public void setQuantity(int quantity) {
		_quantity = quantity;
	}
	public double getUnitCost() {
		return _unitCost;
	}
	public void setUnitCost(double unitCost) {
		_unitCost = unitCost;
	}
	public Order getOrder() {
		return _order_fk;
	}
	public void setOrder(Order order) {
		_order_fk = order;
	}
	public Item getItem() {
		return _item_fk;
	}
	public void setItem(Item item) {
		_item_fk = item;
	}

    // ======================================
    // =             Attributes             =
    // ======================================
	private int _quantity;
	private double _unitCost;
	private Order _order_fk;
	private Item _item_fk;
	
}
