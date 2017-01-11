package com.yaps.petstore.server.cart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.yaps.petstore.common.dto.ItemDTO;
import com.yaps.petstore.common.dto.ShoppingCartItemDTO;
import com.yaps.petstore.common.exception.ObjectNotFoundException;
import com.yaps.petstore.server.domain.item.Item;
import com.yaps.petstore.server.domain.item.ItemDAO;

public class ShoppingCartBean implements ShoppingCart{
	
	private static Map _shoppingCart;
	private static final ItemDAO _itemDAO = new ItemDAO();

	@Override
	public Map getCart() {
		return _shoppingCart;
	}

	@Override
	public Collection getItems() {
		ShoppingCartItemDTO shoppingCartItemDTO;
		Collection shoppingCartItemDTOs = new ArrayList();
		Item item;
		for(Object id : _shoppingCart.keySet()) {
			try {
				item = (Item) _itemDAO.findByPrimaryKey((String)id);
				shoppingCartItemDTO = new ShoppingCartItemDTO(item.getId(), item.getName(), item.getProduct().getDescription(),(int)_shoppingCart.get(item.getId()) , item.getUnitCost());
				shoppingCartItemDTOs.add(shoppingCartItemDTO);
			} catch (ObjectNotFoundException e) {
				e.printStackTrace();
			}
        }
		return shoppingCartItemDTOs;
	}

	@Override
	public void addItem(String itemId) {
		_shoppingCart.put(itemId, 1);
	}

	@Override
	public void removeItem(String itemId) {
		_shoppingCart.remove(itemId);
	}

	@Override
	public void updateItemQuantity(String itemId, int newQty) {
		_shoppingCart.replace(itemId, newQty);
	}

	@Override
	public Double getTotal() {
		Item item;
		Double i = 0.0;
		for(Object id : _shoppingCart.keySet()) {
			try {
				item = (Item) _itemDAO.findByPrimaryKey((String)id);
				i+=item.getUnitCost()*(int)_shoppingCart.get(item.getId());
			} catch (ObjectNotFoundException e) {
				e.printStackTrace();
			}
        }
		return i;
	}

	@Override
	public void empty() {
		_shoppingCart.clear();
		
	}

}
