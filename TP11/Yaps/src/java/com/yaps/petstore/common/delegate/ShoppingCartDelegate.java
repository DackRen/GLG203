package com.yaps.petstore.common.delegate;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.yaps.petstore.common.locator.ServiceLocator;
import com.yaps.petstore.server.cart.ShoppingCart;
import com.yaps.petstore.server.cart.ShoppingCartHome;

public class ShoppingCartDelegate {
	private String _sessionId;
//	private static boolean cache=false;
	
	private ShoppingCart shoppingCart;

	private static HashMap<String, ShoppingCart> sessionMap = new HashMap<String, ShoppingCart>();

	public ShoppingCartDelegate(String id) throws RemoteException{
		_sessionId = id;
		
		shoppingCart=getShoppingCart();
		
		sessionMap.put(_sessionId,shoppingCart);
	}
	
	public String getSessionId() {
		return _sessionId;
	}

	public void empty() throws RemoteException {
		//getShoppingCart().empty();
		shoppingCart.empty();
	}

	public Map<?, ?> getCart() throws RemoteException {
		return shoppingCart.getCart();
	}

	public Collection<?> getItems() throws RemoteException {
		return shoppingCart.getItems();
	}
	
	public void addItem(String parameter) throws RemoteException {
		shoppingCart.addItem(parameter);	
	}

	public void updateItemQuantity(String itemId, int i) throws RemoteException {
		shoppingCart.updateItemQuantity(itemId, i);	
	}

	public void removeItem(String itemId) throws RemoteException {
		shoppingCart.removeItem(itemId);
	}
	
	public Double getTotal() throws RemoteException {
		return shoppingCart.getTotal();
	}
	
    // ======================================
    // =            Private methods         =
    // ======================================
    private ShoppingCart getShoppingCart() throws RemoteException {
    	ShoppingCart shoppingCartRemote = null;
    	if(sessionMap.containsKey(_sessionId)){
    		return sessionMap.get(_sessionId);
    	}
    	else{
    		try {
                // Looks up for the home interface
            	shoppingCartRemote = (ShoppingCart) ServiceLocator.getInstance().getHome(ShoppingCartHome.JNDI_NAME);
            } catch (Exception e) {
                throw new RemoteException("Lookup or Create exception", e);
            }
    	}
        return shoppingCartRemote;
    }

}
