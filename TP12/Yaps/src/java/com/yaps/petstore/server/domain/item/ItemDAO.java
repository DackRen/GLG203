package com.yaps.petstore.server.domain.item;

import com.yaps.petstore.common.exception.DataAccessException;
import com.yaps.petstore.common.exception.ObjectNotFoundException;
import com.yaps.petstore.common.logging.Trace;
import com.yaps.petstore.server.domain.DomainObject;
import com.yaps.petstore.server.domain.category.Category;
import com.yaps.petstore.server.domain.product.Product;
import com.yaps.petstore.server.util.persistence.AbstractDataAccessObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

/**
 * This class does all the database access for the class Item.
 *
 * @see Item
 */
public final class ItemDAO extends AbstractDataAccessObject<String, Item> {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static final String COUNTER_NAME = "Item";
    
	protected String getCounterName() {
		return COUNTER_NAME;
	}

    // ======================================
    // =            Constructors            =
    // ======================================
    public ItemDAO() {
    	this("petstorePU");
    }
    
    public ItemDAO(String persistenceUnitName) {
    	super(persistenceUnitName);
    }
    // ======================================
    // =           Business methods         =
    // ======================================

	public Collection<?> search(String keyword) throws ObjectNotFoundException {
		Query query = _em.createNamedQuery("Item.findAll");
		query.setParameter("id", keyword);
		List<Item> entities = query.getResultList();
		if (entities.isEmpty())
			throw new ObjectNotFoundException();
		return entities;
	}

	public Collection findAllInProduct(String productId) throws ObjectNotFoundException{
		Query query = _em.createNamedQuery("Item.findAllInProduct");
		query.setParameter("productId", productId);
		List<Product> entities = query.getResultList();
		if (entities.isEmpty())
			throw new ObjectNotFoundException();
		return entities;
	}
   
}
