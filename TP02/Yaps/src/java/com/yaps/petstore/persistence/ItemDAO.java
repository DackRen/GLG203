package com.yaps.petstore.persistence;

import java.util.Collection;
import com.yaps.petstore.domain.Item;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.DuplicateKeyException;
import com.yaps.petstore.exception.ObjectNotFoundException;

public class ItemDAO extends DataAccessObject{
	
	private static final String HASHTABLE_FILE_NAME = "persistentItem.ser";
	
	public ItemDAO() {
		super(HASHTABLE_FILE_NAME);
	}

	public void finalize(){
		super.finalize();		
	}
	
	public Item find(String id) throws ObjectNotFoundException,CheckException{
		checkId(id);
        return (Item) super.select(id);
	}

	@SuppressWarnings("rawtypes")
	public Collection findAll() throws ObjectNotFoundException{
		return selectAll();
	}

	public void insert(Item item) throws DuplicateKeyException,ObjectNotFoundException,CheckException{
		item.checkData();
		super.insert(item, item.getId());
		
	}

	public void update(Item item) throws CheckException, ObjectNotFoundException, DuplicateKeyException{
    	String id = item.getId();
    	checkId(id);
    	item.checkData();
    	remove(id);
		insert(item);
		
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

