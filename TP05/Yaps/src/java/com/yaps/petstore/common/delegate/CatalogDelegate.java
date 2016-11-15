package com.yaps.petstore.common.delegate;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Collection;

import com.yaps.petstore.common.dto.CategoryDTO;
import com.yaps.petstore.common.dto.ItemDTO;
import com.yaps.petstore.common.dto.ProductDTO;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.CreateException;
import com.yaps.petstore.common.exception.FinderException;
import com.yaps.petstore.common.exception.RemoveException;
import com.yaps.petstore.common.exception.UpdateException;
import com.yaps.petstore.common.rmi.RMIConstant;
import com.yaps.petstore.server.service.catalog.CatalogServiceRemote;

public class CatalogDelegate implements RMIConstant{
	// ======================================
	// =             Attributes             =
	// ======================================
	private static CatalogServiceRemote catalogServiceRemote;
	
	// ======================================
	// =           Business methods         =
	// ======================================

	public static CategoryDTO createCategory(final CategoryDTO categoryDTO) throws CreateException, CheckException, RemoteException {
		return getCatalogService().createCategory(categoryDTO);
	}
	
	public static void updateCategory(CategoryDTO categoryDTO) throws UpdateException, CheckException, RemoteException{
		getCatalogService().updateCategory(categoryDTO);
	}

	public static CategoryDTO findCategory(final String categoryId) throws FinderException, CheckException, RemoteException {
		return getCatalogService().findCategory(categoryId);
	}
	
	public static Collection findCategories() throws FinderException, RemoteException{
		return getCatalogService().findCategories();
	}
	
    public static void deleteCategory(final String categoryId) throws RemoveException, CheckException, RemoteException {
    	getCatalogService().deleteCategory(categoryId);
    }
    
    public static void createProduct(final ProductDTO productDTO) throws CreateException, CheckException, RemoteException {
    	getCatalogService().createProduct(productDTO);
    }
    
	public static void updateProduct(ProductDTO productDTO) throws UpdateException, CheckException, RemoteException{
		getCatalogService().updateProduct(productDTO);
	}

	public static ProductDTO findProduct(final String productId) throws CheckException, RemoteException, FinderException{
		return getCatalogService().findProduct(productId);
	}
	
	public static Collection findProducts() throws FinderException, RemoteException{
		return getCatalogService().findProducts();
	}
	
	public static void deleteProduct(final String productId) throws RemoveException, CheckException, RemoteException {
    	getCatalogService().deleteProduct(productId);
    }

	public static void createItem(ItemDTO itemDTO) throws CheckException, CreateException, RemoteException{
		getCatalogService().createItem(itemDTO);
	}
	
	public static void updateItem(ItemDTO itemDTO) throws UpdateException, CheckException, RemoteException{
		getCatalogService().updateItem(itemDTO);
	}

	public static ItemDTO findItem(String itemId) throws RemoteException, FinderException, CheckException{
		return getCatalogService().findItem(itemId);
	}
	
	public static Collection findItems() throws FinderException, RemoteException{
		return getCatalogService().findItems();
	}

	public static void deleteItem(String itemId) throws RemoveException, CheckException, RemoteException {
		getCatalogService().deleteItem(itemId);;
	}

	// ======================================
	// =            Private methods         =
	// ======================================
	private static CatalogServiceRemote getCatalogService() throws RemoteException {
		try {
			catalogServiceRemote = (CatalogServiceRemote) Naming.lookup(CATALOG_SERVICE);
			} catch (Exception e) {
				throw new RemoteException("Lookup exception", e);
				}
		return catalogServiceRemote;
		}
	
}