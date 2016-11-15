package com.yaps.petstore.persistence;

import java.util.Collection;
import com.yaps.petstore.domain.Product;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.DuplicateKeyException;
import com.yaps.petstore.exception.ObjectNotFoundException;

public class ProductDAO extends DataAccessObject{
	
	private static final String HASHTABLE_FILE_NAME = "persistentProduct.ser";
	
	public ProductDAO() {
		super(HASHTABLE_FILE_NAME);
	}

	public void finalize(){
		super.finalize();		
	}
	
	public Product find(String id) throws ObjectNotFoundException,CheckException{
		checkId(id);
        return (Product) super.select(id);
	}

	@SuppressWarnings("rawtypes")
	public Collection findAll() throws ObjectNotFoundException{
		return selectAll();
	}

	public void insert(Product product) throws DuplicateKeyException,ObjectNotFoundException,CheckException{
		product.checkData();
		super.insert(product, product.getId());
		
	}

	public void update(Product product) throws CheckException, ObjectNotFoundException, DuplicateKeyException{
    	String id = product.getId();
    	checkId(id);
    	product.checkData();
    	remove(id);
		insert(product);
		
	}
	public void remove(String id) throws ObjectNotFoundException {
		super.remove(id);
		
	}

	public int getUniqueId() {
		String s = null;
    	int result = 0;
    	do {
    		result = (int) (Math.random() * 100000);
    		s = Integer.toString(result);
    		try {
				select(s);
			} catch (ObjectNotFoundException e) {
				return result;
			}
    	} while ( true );
	}

}
