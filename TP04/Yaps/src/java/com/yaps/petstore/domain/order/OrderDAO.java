package com.yaps.petstore.domain.order;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.yaps.petstore.domain.DomainObject;
import com.yaps.petstore.domain.customer.Customer;
import com.yaps.petstore.persistence.AbstractDataAccessObject;

public class OrderDAO extends AbstractDataAccessObject{

    // ======================================
    // =             Attributes             =
    // ======================================
    private static final String TABLE = "T_ORDER";
    private static final String COLUMNS = "ID, ORDERDATE, FIRSTNAME, LASTNAME, STREET1, STREET2, CITY, STATE, ZIPCODE, COUNTRY, CREDITCARDNUMBER, CREDITCARDTYPE, CREDITCARDEXPIRYDATE ,CUSTOMER_FK";
    // Used to get a unique id with the UniqueIdGenerat
    private static final String COUNTER_NAME = "Order";
    private static final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

    // ======================================
    // =           Business methods         =
    // ======================================

	@Override
	protected String getInsertSqlStatement(DomainObject object) {
		final Order order = (Order) object;
        final String sql;
        
        sql = "INSERT INTO " + TABLE + "(" + COLUMNS + ") VALUES ('" + 
        		order.getId() + "', '" + 
        		sdf.format(order.getOrderDate()) + "', '" + //watch out the time type in the database
        		order.getFirstname() + "', '" + 
        		order.getLastname()+ "', '" + 
        		order.getStreet1() + "', '" + 
        		order.getStreet2() + "', '" + 
        		order.getCity() + "', '" + 
        		order.getState()+ "', '" +
        		order.getZipcode() + "', '" +
        		order.getCountry() + "', '" +
        		order.getCreditCardNumber() + "', '" +
        		order.getCreditCardType() + "', '" +
        		order.getCreditCardExpiryDate() + "', '" +
        		order.getCustomer().getId()+ "' )";
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
		final Order order = (Order) object;
        final String sql;        
        sql = "UPDATE " + TABLE +       		
        		" SET ORDERDATE = '" +  sdf.format(order.getOrderDate()) +
        		"', FIRSTNAME = '"+order.getFirstname()+ 
        		"', LASTNAME = '" + order.getLastname() + 
        		"', STREET1 = '" + order.getStreet1() + 
        		"', STREET2 = '"+order.getStreet2()+ 
        		"', CITY = '"+order.getCity()+ 
        		"', STATE = '"+order.getState()+ 
        		"', ZIPCODE = '"+order.getZipcode()+ 
        		"', COUNTRY = '"+order.getCountry()+ 
        		"', CREDITCARDNUMBER = '"+order.getCreditCardNumber()+ 
        		"', CREDITCARDTYPE = '"+order.getCreditCardType()+ 
        		"', CREDITCARDEXPIRYDATE = '"+order.getCreditCardExpiryDate()+ 
        		"', CUSTOMER_FK = '"+order.getCustomer().getId()+ 
        		"' WHERE ID = '" + order.getId() + "' ";
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
		final Order order;
		order = new Order(resultSet.getString(1), resultSet.getDate(2),resultSet.getString(3),resultSet.getString(4), resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),
				resultSet.getString(8),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11),resultSet.getString(12),resultSet.getString(13), new Customer(resultSet.getString(14)));
        return order;
	}
	
	@Override
	protected String getCounterName() {
		return COUNTER_NAME;
	}


}
