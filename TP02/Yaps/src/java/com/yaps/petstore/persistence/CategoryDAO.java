package com.yaps.petstore.persistence;

import java.util.Collection;
import com.yaps.petstore.domain.Category;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.DuplicateKeyException;
import com.yaps.petstore.exception.ObjectNotFoundException;

public class CategoryDAO extends DataAccessObject{
	
	private static final String HASHTABLE_FILE_NAME = "persistentCategory.ser";
	
	public CategoryDAO() {
		super(HASHTABLE_FILE_NAME);
	}
	
	public void finalize(){
		super.finalize();
		
	}

	public Category find(String id) throws ObjectNotFoundException,CheckException{
        checkId(id);
        return (Category) super.select(id);
	}
	
	@SuppressWarnings("rawtypes")
	public Collection findAll() throws ObjectNotFoundException{
		return selectAll();
	}
	
	public void insert(Category category) throws DuplicateKeyException,CheckException, ObjectNotFoundException{
		category.checkData();
		super.insert(category, category.getId());
	}
	
	public void update(Category category) throws ObjectNotFoundException,CheckException, DuplicateKeyException{
	    	String id = category.getId();
	    	checkId(id);
	    	category.checkData();
	    	remove(id);
			insert(category);
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
