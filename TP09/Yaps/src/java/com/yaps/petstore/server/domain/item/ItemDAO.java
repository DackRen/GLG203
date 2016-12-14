package com.yaps.petstore.server.domain.item;

import com.yaps.petstore.common.exception.DataAccessException;
import com.yaps.petstore.common.exception.ObjectNotFoundException;
import com.yaps.petstore.common.logging.Trace;
import com.yaps.petstore.server.domain.DomainObject;
import com.yaps.petstore.server.domain.product.Product;
import com.yaps.petstore.server.util.persistence.AbstractDataAccessObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class does all the database access for the class Item.
 *
 * @see Item
 */
public final class ItemDAO extends AbstractDataAccessObject {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static final String TABLE = "T_ITEM";
    private static final String COLUMNS = "ID, NAME, UNITCOST ,  IMAGEPATH, PRODUCT_FK";
    // Used to get a unique id with the UniqueIdGenerator
    private static final String COUNTER_NAME = "Item";

    // ======================================
    // =           Business methods         =
    // ======================================
    /**
     * This method return all the items from the database for a given product id.
     *
     * @param productId
     * @return collection of Items
     * @throws ObjectNotFoundException is thrown if the collection is empty
     * @throws DataAccessException     is thrown if there's a persistent problem
     */
    public Collection findAll(final String productId) throws ObjectNotFoundException {
    	return selectAll(productId);
    }

    /**
     * This method return all the items from the database for a given product id.
     *
     * @param productId
     * @return collection of Items
     * @throws ObjectNotFoundException is thrown if the collection is empty
     * @throws DataAccessException     is thrown if there's a persistent problem
     */
    public Collection selectAll(final String productId) throws ObjectNotFoundException {
        final String mname = "selectAll";
        Trace.entering(getCname(), mname, productId);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        final Collection items = new ArrayList();

        try {
            // Gets a database connection
            connection = getConnection();
            statement = connection.createStatement();

            // Select a Row
            final String sql = "SELECT " + COLUMNS + " FROM " + TABLE + " WHERE PRODUCT_FK = '" + productId + "'";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                // Set data to the collection
                items.add(transformResultset2DomainObject(resultSet));
            }

            if (items.isEmpty())
                throw new ObjectNotFoundException();

        } catch (SQLException e) {
            // A Severe SQL Exception is caught
            displaySqlException(e);
            throw new DataAccessException("Cannot get data from the database: " + e.getMessage(), e);
        } finally {
            // Close
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                displaySqlException("Cannot close connection", e);
                throw new DataAccessException("Cannot close the database connection", e);
            }
        }
        return items;
    }
    
    public Collection search(String keyword) throws ObjectNotFoundException{
    	//TODO:s
    	final String mname = "search";
        Trace.entering(getCname(), mname, keyword);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        final Collection items = new ArrayList();

        try {
            // Gets a database connection
            connection = getConnection();
            statement = connection.createStatement();

            // Select a Row
            final String sql = "SELECT " + COLUMNS + " FROM " + TABLE + " WHERE ID LIKE '%" + keyword + "%'";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                // Set data to the collection
                items.add(transformResultset2DomainObject(resultSet));
            }

            if (items.isEmpty())
                throw new ObjectNotFoundException();

        } catch (SQLException e) {
            // A Severe SQL Exception is caught
            displaySqlException(e);
            throw new DataAccessException("Cannot get data from the database: " + e.getMessage(), e);
        } finally {
            // Close
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                displaySqlException("Cannot close connection", e);
                throw new DataAccessException("Cannot close the database connection", e);
            }
        }
        return items;
    }

    protected String getInsertSqlStatement(final DomainObject object) {
        final Item item = (Item) object;
        final String sql;
        sql = "INSERT INTO " + TABLE + "(" + COLUMNS + ") VALUES ('" + item.getId() + "', '" + item.getName() + "', '" + item.getUnitCost() + "', '" + item.getImagePath() + "', '" + item.getProduct().getId() + "' )";
        return sql;
    }

    protected String getDeleteSqlStatement(final String id) {
        final String sql;
        sql = "DELETE FROM " + TABLE + " WHERE ID = '" + id + "'";
        return sql;
    }

    protected String getUpdateSqlStatement(final DomainObject object) {
        final Item item = (Item) object;
        final String sql;
        sql = "UPDATE " + TABLE + " SET NAME = '" + item.getName() + "', UNITCOST = '" + item.getUnitCost() + "', IMAGEPATH = '" + item.getImagePath() + "', PRODUCT_FK = '" + item.getProduct().getId() + "' WHERE ID = '" + item.getId() + "' ";
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
        final Item item;
        item = new Item(resultSet.getString(1), resultSet.getString(2), resultSet.getDouble(3), new Product(resultSet.getString(5)));
        item.setImagePath(resultSet.getString(4));
        return item;
    }

	protected String getCounterName() {
		return COUNTER_NAME;
	}
}
