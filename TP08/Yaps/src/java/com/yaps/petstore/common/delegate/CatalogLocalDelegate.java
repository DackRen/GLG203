package com.yaps.petstore.common.delegate;

import java.rmi.RemoteException;

import com.yaps.petstore.server.service.catalog.CatalogService;

public class CatalogLocalDelegate extends CatalogDelegate{

	// ======================================
    // =             Attributes             =
    // ======================================
    private static CatalogServiceRemote catalogServiceRemote;

    // ======================================
    // =            Private methods         =
    // ======================================
    protected CatalogServiceRemote getCatalogService() throws RemoteException {
    	if ( catalogServiceRemote == null )
    		catalogServiceRemote = new CatalogService();
    	return catalogServiceRemote;
    }

}
