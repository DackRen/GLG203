package com.yaps.petstore.server.domain.comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.yaps.petstore.server.domain.DomainObject;
import com.yaps.petstore.server.domain.customer.Customer;
import com.yaps.petstore.server.domain.order.Order;
import com.yaps.petstore.server.util.persistence.AbstractDataAccessObject;

public class CommentDAO extends AbstractDataAccessObject{
	 // ======================================
    // =             Attributes             =
    // ======================================
    private static final String TABLE = "T_COMMENT";
    private static final String COLUMNS = "ID, TITLE, TEXT, DATE, CUSTOMER_FK";
    // Used to get a unique id with the UniqueIdGenerator
    private static final String COUNTER_NAME = "Comment";

    // ======================================
    // =           Business methods         =
    // ======================================
    protected String getInsertSqlStatement(final DomainObject object) {
        final Comment comment = (Comment) object;
        final String sql;
        sql = "INSERT INTO " + TABLE + "(" + COLUMNS + ") VALUES ('" + comment.getId() + "', '" 
        + comment.getTitle() + "', '" 
        + comment.getText() + "', '" 
        + new Timestamp(comment.getDate().getTime()) + "', '" 
        + comment.getCustomer().getId() + "' )";
        return sql;
    }

    protected String getDeleteSqlStatement(final String id) {
        final String sql;
        sql = "DELETE FROM " + TABLE + " WHERE ID = '" + id + "'";
        return sql;
    }

    protected String getUpdateSqlStatement(final DomainObject object) {
        final Comment comment = (Comment) object;
        final String sql;
        //TODO:
        //upate DATE = '" + order.getStreet1() + "',?
        sql = "UPDATE " + TABLE + " SET TITLE = '" + comment.getTitle() + "', TEXT = '" + comment.getText() + "', CUSTOMER_FK = '" + comment.getCustomer().getId() + "' WHERE ID = '" + comment.getId() + "' ";
        return sql;
    }

    protected String getSelectSqlStatement(final String id) {
        final String sql;
        sql = "SELECT " + COLUMNS + " FROM " + TABLE + " WHERE ID = '" + id + "' ";
        return sql;
    }

    protected String getSelectAllSqlStatement() {
        final String sql;
        sql = "SELECT " + COLUMNS + " FROM " + TABLE;
        return sql;
    }

    protected DomainObject transformResultset2DomainObject(final ResultSet resultSet) throws SQLException {
        final Comment comment;
        comment = new Comment(resultSet.getString(1),resultSet.getString(2), resultSet.getString(3), new Customer(resultSet.getString(5)), resultSet.getTimestamp(4));
        return comment;
    }

	protected String getCounterName() {
		return COUNTER_NAME;
	}

}
