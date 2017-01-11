package com.yaps.petstore.common.delegate;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Map;

import com.yaps.petstore.common.locator.ServiceLocator;
import com.yaps.petstore.server.cart.ShoppingCart;
import com.yaps.petstore.server.service.catalog.CatalogServiceHome;

public class ShoppingCartDelegate {
	private String _sessionId;

	public ShoppingCartDelegate(String id) {
		this._sessionId = id;
	}
	
	public String getSessionId() {
		return _sessionId;
	}

	public void empty() throws RemoteException {
		getShoppingCart().empty();
	}

	public Map<?, ?> getCart() throws RemoteException {
		return getShoppingCart().getCart();
	}

	public Collection<?> getItems() throws RemoteException {
		return getShoppingCart().getItems();
	}
	
	public void addItem(String parameter) throws RemoteException {
		getShoppingCart().addItem(parameter);	
	}

	public void updateItemQuantity(String itemId, int i) throws RemoteException {
		getShoppingCart().updateItemQuantity(itemId, i);	
	}

	public void removeItem(String itemId) throws RemoteException {
		getShoppingCart().removeItem(itemId);
	}
	
	
	public Double getTotal() throws RemoteException {
		return getShoppingCart().getTotal();
	}
	
    // ======================================
    // =            Private methods         =
    // ======================================
    private static ShoppingCart getShoppingCart() throws RemoteException {
    	ShoppingCart shoppingCartRemote = null;
        try {
            // Looks up for the home interface
        	shoppingCartRemote = (ShoppingCart) ServiceLocator.getInstance().getHome(CatalogServiceHome.JNDI_NAME);
        } catch (Exception e) {
            throw new RemoteException("Lookup or Create exception", e);
        }

        return shoppingCartRemote;
    }


}
