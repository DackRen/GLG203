package com.yaps.petstore.common.delegate;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Collection;

import com.yaps.petstore.common.dto.CustomerDTO;
import com.yaps.petstore.common.dto.OrderDTO;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.CreateException;
import com.yaps.petstore.common.exception.FinderException;
import com.yaps.petstore.common.exception.ObjectNotFoundException;
import com.yaps.petstore.common.exception.RemoveException;
import com.yaps.petstore.common.exception.UpdateException;
import com.yaps.petstore.common.rmi.RMIConstant;
import com.yaps.petstore.server.service.customer.CustomerServiceRemote;
import com.yaps.petstore.server.service.order.OrderServiceRemote;

public class CustomerDelegate implements RMIConstant{
	 // ======================================
    // =             Attributes             =
    // ======================================
    private static CustomerServiceRemote customerServiceRemote;

    // ======================================
    // =           Business methods         =
    // ======================================

    public static CustomerDTO createCustomer(final CustomerDTO customerDTO) throws CreateException, CheckException, RemoteException {
        return getCustomerService().createCustomer(customerDTO);
    }
    
	public static void updateCustomer(CustomerDTO customerDTO) throws UpdateException, CheckException, RemoteException{
		getCustomerService().updateCustomer(customerDTO);
	}
    
    public static CustomerDTO findCustomer(final String customerId) throws FinderException, CheckException, RemoteException {
        return getCustomerService().findCustomer(customerId);
    }
  
	public static Collection findCustomers() throws FinderException, RemoteException{
		return getCustomerService().findCustomers();
	}

    public static void deleteCustomer(final String customerId) throws RemoveException, CheckException, RemoteException {
    	getCustomerService().deleteCustomer(customerId);
    }

    // ======================================
    // =            Private methods         =
    // ======================================
    private static CustomerServiceRemote getCustomerService() throws RemoteException {
        try {
            customerServiceRemote = (CustomerServiceRemote) Naming.lookup(CUSTOMER_SERVICE_URL);
        } catch (Exception e) {
            throw new RemoteException("Lookup exception", e);
        }
        return customerServiceRemote;
    }
}


