package com.yaps.petstore.server.domain.product;

import com.yaps.petstore.common.exception.DataAccessException;
import com.yaps.petstore.common.exception.ObjectNotFoundException;
import com.yaps.petstore.common.logging.Trace;
import com.yaps.petstore.server.domain.DomainObject;
import com.yaps.petstore.server.domain.category.Category;
import com.yaps.petstore.server.domain.orderline.OrderLine;
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
 * This class does all the database access for the class Product.
 *
 * @see Product
 */
public final class ProductDAO extends AbstractDataAccessObject<String, Product> {

    // ======================================
    // =             Attributes             =
    // ======================================
    // Used to get a unique id with the UniqueIdGenerator
    private static final String COUNTER_NAME = "Product";
    
    // ======================================
    // =            Constructors            =
    // ======================================
    public ProductDAO() {
    	this("petstorePU");
    }
    
    public ProductDAO(String persistenceUnitName) {
    	super(persistenceUnitName);
    }

    // ======================================
    // =           Business methods         =
    // ======================================

	protected String getCounterName() {
		return COUNTER_NAME;
	}

	public Collection<Category> findAllInCategory(String categoryId) throws ObjectNotFoundException{
		Query query = _em.createNamedQuery("Product.findAllInCategory");
    	query.setParameter("categoryId", categoryId);
    	List<Category> entities = query.getResultList();
        if (entities.isEmpty())
            throw new ObjectNotFoundException();
		return entities;
	}

}
