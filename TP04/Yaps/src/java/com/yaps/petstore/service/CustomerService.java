package com.yaps.petstore.service;

import java.util.Collection;

import com.yaps.petstore.domain.customer.Customer;
import com.yaps.petstore.domain.customer.CustomerDAO;
import com.yaps.petstore.domain.order.Order;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.CreateException;
import com.yaps.petstore.exception.DuplicateKeyException;
import com.yaps.petstore.exception.FinderException;
import com.yaps.petstore.exception.ObjectNotFoundException;
import com.yaps.petstore.exception.RemoveException;
import com.yaps.petstore.exception.UpdateException;

public class CustomerService {
	private static final CustomerDAO _customerDAO = new CustomerDAO();

	
	// NOTICE!!! findCustomer"s"
	// There is a s !!!!!!!!
	public Collection findCustomers() throws ObjectNotFoundException{
		return _customerDAO.findAll();
	}

	public Customer findCustomer(String customerId) throws ObjectNotFoundException,CheckException{
		final Customer customer;
		if(customerId==null||customerId.equals(""))
			throw new CheckException("null id");
		try{
			customer = (Customer) _customerDAO.findByPrimaryKey(customerId);
		}catch(ObjectNotFoundException e){
			throw new ObjectNotFoundException();
		}
		return customer;
	}

	public Customer createCustomer(Customer customer) throws CheckException, CreateException{
		if(customer==null)
			throw new CreateException("coustomer is null");
		customer.checkData();
		_customerDAO.insert(customer);
		return customer;	

	}

	public void deleteCustomer(final String customerId) throws RemoveException{
		try {
			_customerDAO.findByPrimaryKey(customerId);
        } catch (FinderException e) {
        	throw new RemoveException("Customer must exist to be deleted");
        }
	       // Deletes the object
        try {
        	_customerDAO.remove(customerId);
        } catch (ObjectNotFoundException e) {
            throw new RemoveException("Category must exist to be deleted");
        }
		
	}

	public void updateCustomer(Customer customer) throws UpdateException,CheckException{
		if(customer==null)
			throw new UpdateException("null object");
		checkId(customer.getId());
		customer.checkData();
		try {
			_customerDAO.update(customer);
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getUniqueId() {
		return _customerDAO.getUniqueId("Customer");
	}
	//
	private void checkId(final String id) throws CheckException {
    	if ( id == null || id.equals("") )
    		throw new CheckException("Id should not be null or empty");    	
    }



}
