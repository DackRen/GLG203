package com.yaps.petstore.server.cart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateful;

import com.yaps.petstore.common.dto.ShoppingCartItemDTO;
import com.yaps.petstore.common.exception.ObjectNotFoundException;
import com.yaps.petstore.server.domain.item.Item;
import com.yaps.petstore.server.domain.item.ItemDAO;
import com.yaps.petstore.server.service.AbstractRemoteService;

@Stateful (name="ShoppingCartSB")
public class ShoppingCartBean extends AbstractRemoteService implements ShoppingCart{
	
	private Map<String, Integer> _shoppingCart = new HashMap<String, Integer>();
	private static final ItemDAO _itemDAO = new ItemDAO();

	@Override
	public Map<String, Integer> getCart() {
		return _shoppingCart;
	}

	@Override
	public Collection<ShoppingCartItemDTO> getItems() {
		ShoppingCartItemDTO shoppingCartItemDTO;
		Collection<ShoppingCartItemDTO> shoppingCartItemDTOs = new ArrayList<ShoppingCartItemDTO>();
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
		Double total = 0.0;
		for(Object id : _shoppingCart.keySet()) {
			try {
				item = (Item) _itemDAO.findByPrimaryKey((String)id);
				total+=item.getUnitCost()*(Integer)_shoppingCart.get(item.getId());
			} catch (ObjectNotFoundException e) {
				e.printStackTrace();
			}
        }
		return total;
	}

	@Override
	public void empty() {
		_shoppingCart.clear();
		
	}

}
