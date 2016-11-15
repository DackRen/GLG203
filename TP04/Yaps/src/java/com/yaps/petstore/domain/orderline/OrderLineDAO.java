package com.yaps.petstore.domain.orderline;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.yaps.petstore.domain.DomainObject;
import com.yaps.petstore.domain.item.Item;
import com.yaps.petstore.domain.order.Order;
import com.yaps.petstore.exception.ObjectNotFoundException;
import com.yaps.petstore.persistence.AbstractDataAccessObject;

public class OrderLineDAO extends AbstractDataAccessObject{
	
    // ======================================
    // =             Attributes             =
    // ======================================
    private static final String TABLE = "T_ORDER_LINE";
    private static final String COLUMNS = "ID, QUANTITY, UNITCOST, ORDER_FK , ITEM_FK";
    // Used to get a unique id with the UniqueIdGenerator
    private static final String COUNTER_NAME = "OrderLine";

    // ======================================
    // =           Business methods         =
    // ======================================

	@Override
	protected String getInsertSqlStatement(DomainObject object) {
		final OrderLine orderLine = (OrderLine) object;
        final String sql;
        sql = "INSERT INTO " + TABLE + "(" + COLUMNS + ") VALUES ('" + orderLine.getId() + "', '" + orderLine.getQuantity() + "', '" + orderLine.getUnitCost() + "', '" + orderLine.getOrder().getId() + "', '" +orderLine.getItem().getId()+ "' )";
        return sql;
	}

	@Override
	protected String getDeleteSqlStatement(String id) {
		final String sql;
        sql = "DELETE FROM " + TABLE + " WHERE ID = '" + id + "'";
        return sql;
	}

	@Override
	protected String getUpdateSqlStatement(DomainObject object) {
		final OrderLine orderLine = (OrderLine) object;
        final String sql;
        sql = "UPDATE " + TABLE + " SET QUANTITY = '" + orderLine.getQuantity() +"', UNITCOST = '"+orderLine.getUnitCost()+ "', ORDER_FK = '" + orderLine.getOrder() + "', ITEM_FK = '" + orderLine.getItem().getId() + "' WHERE ID = '" + orderLine.getId() + "' ";
        return sql;
	}

	@Override
	protected String getSelectSqlStatement(String id) {
        final String sql;
        sql = "SELECT " + COLUMNS + " FROM " + TABLE + " WHERE ID = '" + id + "' ";
        return sql;
	}

	@Override
	protected String getSelectAllSqlStatement() {
        final String sql;
        sql = "SELECT " + COLUMNS + " FROM " + TABLE;
        return sql;
	}

	@Override
	protected DomainObject transformResultset2DomainObject(ResultSet resultSet) throws SQLException {
		final OrderLine orderLine;
		orderLine = new OrderLine(resultSet.getString(1), resultSet.getInt(2),resultSet.getDouble(3), new Order(resultSet.getString(4)), new Item(resultSet.getString(5)));
        return orderLine;
	}
	
	@Override
	protected String getCounterName() {
		return COUNTER_NAME;
	}
	
	//@Override
	/*
	 * weather should override father's method, now this seem ruin consistency of logic?
	 * receive a order ID and return all this ID's orderLine by a collection
	 * find all OrderLines firstly then add every OrderLine which belong this order id 
	 */
	public Collection findAll(String orderId) throws ObjectNotFoundException {
		Collection objects = new ArrayList();
		for (OrderLine ol : (ArrayList<OrderLine>)findAll()) {  
            if(ol.getOrder().getId().equals(orderId)){
            	objects.add(ol);
            }
        }
		return objects;
	}

}
