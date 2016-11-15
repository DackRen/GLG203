package com.yaps.petstore.server.service.catalog;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.yaps.petstore.common.dto.CategoryDTO;
import com.yaps.petstore.common.dto.ItemDTO;
import com.yaps.petstore.common.dto.ProductDTO;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.CreateException;
import com.yaps.petstore.common.exception.FinderException;
import com.yaps.petstore.common.exception.ObjectNotFoundException;
import com.yaps.petstore.common.exception.RemoveException;
import com.yaps.petstore.common.exception.UpdateException;
import com.yaps.petstore.common.logging.Trace;
import com.yaps.petstore.server.domain.category.Category;
import com.yaps.petstore.server.domain.category.CategoryDAO;
import com.yaps.petstore.server.domain.item.Item;
import com.yaps.petstore.server.domain.item.ItemDAO;
import com.yaps.petstore.server.domain.product.Product;
import com.yaps.petstore.server.domain.product.ProductDAO;
import com.yaps.petstore.server.service.AbstractRemoteService;

public class CatalogService extends AbstractRemoteService implements CatalogServiceRemote {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CatalogService() throws RemoteException {
	}

	private static final CategoryDAO category_dao = new CategoryDAO();
    private static final ProductDAO product_dao = new ProductDAO();
    private static final ItemDAO item_dao = new ItemDAO();

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) throws CreateException, CheckException, RemoteException {
		final String mname = "createCategory";
        Trace.entering(_cname, mname, categoryDTO);

        if (categoryDTO == null)
            throw new CreateException("category object is null");

        // Transforms DTO into domain object
        final Category category = new Category(categoryDTO.getId(), categoryDTO.getName(), categoryDTO.getDescription());

        category.checkData();

        // Creates the object
        category_dao.insert(category);

        // Transforms domain object into DTO
        final CategoryDTO result = transformCategory2DTO(category);

        Trace.exiting(_cname, mname, result);
        return result;
	}

	@Override
	public CategoryDTO findCategory(String categoryId) throws FinderException, CheckException, RemoteException {
		final String mname = "findCategory";
        Trace.entering(_cname, mname, categoryId);
        try{
        	checkId(categoryId);
        }catch (CheckException e){
        	throw new ObjectNotFoundException();
        }
        // Finds the object
        final Category category = (Category)category_dao.findByPrimaryKey(categoryId);

        // Transforms domain object into DTO
        final CategoryDTO categoryDTO = transformCategory2DTO(category);

        Trace.exiting(_cname, mname, categoryDTO);
        return categoryDTO;
	}

	@Override
	public void deleteCategory(String categoryId) throws RemoveException, CheckException, RemoteException {
        final String mname = "deleteCategory";
        Trace.entering(_cname, mname, categoryId);

    	checkId(categoryId);

        // Checks if the object exists
        try {
        	category_dao.findByPrimaryKey(categoryId);
        } catch (FinderException e) {
        	throw new RemoveException("Category must exist to be deleted");
        }

        // Deletes the object
        try {
        	category_dao.remove(categoryId);
        } catch (ObjectNotFoundException e) {
            throw new RemoveException("Category must exist to be deleted");
        }
	}

	@Override
	public void updateCategory(CategoryDTO categoryDTO) throws UpdateException, CheckException, RemoteException {
        final String mname = "updateCategory";
        Trace.entering(_cname, mname, categoryDTO);

        if (categoryDTO == null)
            throw new UpdateException("Category object is null");

    	checkId(categoryDTO.getId());

    	final Category category;

        // Checks if the object exists
        try {
        	category = (Category)category_dao.findByPrimaryKey(categoryDTO.getId());
        } catch (FinderException e) {
            throw new UpdateException("Category must exist to be updated");
        }

        // Transforms DTO into domain object
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        category.checkData();

        // Updates the object
        try {
        	category_dao.update(category);
        } catch (ObjectNotFoundException e) {
            throw new UpdateException("Category must exist to be updated");
        }
	}

	@Override
	public Collection findCategories() throws FinderException, RemoteException {
        final String mname = "findCategories";
        Trace.entering(_cname, mname);

        // Finds all the objects
        final Collection categories = category_dao.selectAll();

        // Transforms domain objects into DTOs
        final Collection categoriesDTO = transformCategoriesDTO2DTOs(categories);

        Trace.exiting(_cname, mname, new Integer(categoriesDTO.size()));
        return categoriesDTO;
	}

	@Override
	public ProductDTO createProduct(ProductDTO productDTO) throws CreateException, CheckException, RemoteException {
		final String mname = "createProduct";
        Trace.entering(_cname, mname, productDTO);

        if (productDTO == null)
            throw new CreateException("Product object is null");
        
        // Finds the category
        Category category = new Category();
        try {
        	category = (Category)category_dao.findByPrimaryKey(productDTO.getCategoryId());
        } catch (FinderException e) {
            throw new CreateException("Category must exist to create an product");
        }
        
        // Transforms DTO into domain object
        Product product = null;
		product = new Product(productDTO.getId(), productDTO.getName(), productDTO.getDescription(),category);

        product.checkData();

        // Creates the object
        product_dao.insert(product);

        // Transforms domain object into DTO
        final ProductDTO result = transformProduct2DTO(product);

        Trace.exiting(_cname, mname, result);
        return result;
	}

	@Override
	public ProductDTO findProduct(String productId) throws FinderException, CheckException, RemoteException {
		final String mname = "findProduct";
        Trace.entering(_cname, mname, productId);

    	checkId(productId);
        // Finds the object
        final Product product = (Product)product_dao.findByPrimaryKey(productId);

        // Transforms domain object into DTO
        final ProductDTO productDTO = transformProduct2DTO(product);

        Trace.exiting(_cname, mname, product_dao);
        return productDTO;
	}

	@Override
	public void deleteProduct(String productId) throws RemoveException, CheckException, RemoteException {
		final String mname = "deleteProduct";
        Trace.entering(_cname, mname, productId);

    	checkId(productId);

        // Checks if the object exists
        try {
        	product_dao.findByPrimaryKey(productId);
        } catch (FinderException e) {
            throw new RemoveException("Product must exist to be deleted");
        }

        // Deletes the object
        try {
        	product_dao.remove(productId);
        } catch (ObjectNotFoundException e) {
            throw new RemoveException("Product must exist to be deleted");
        }
		
	}

	@Override
	public void updateProduct(ProductDTO productDTO) throws UpdateException, CheckException, RemoteException {
		final String mname = "updateProduct";
        Trace.entering(_cname, mname, productDTO);

        if (productDTO == null)
            throw new UpdateException("Product object is null");

    	checkId(productDTO.getId());

    	final Product product;

        // Checks if the object exists
        try {
        	product = (Product)product_dao.findByPrimaryKey(productDTO.getId());
        } catch (FinderException e) {
            throw new UpdateException("Product must exist to be updated");
        }

        // Transforms DTO into domain object
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        try {
			product.setCategory((Category) category_dao.findByPrimaryKey(productDTO.getCategoryId()));
		} catch (ObjectNotFoundException e1) {
			e1.printStackTrace();
		}

        product.checkData();

        // Updates the object
        try {
        	product_dao.update(product);
        } catch (ObjectNotFoundException e) {
            throw new UpdateException("Product must exist to be updated");
        }
	}

	@Override
	public Collection findProducts() throws FinderException, RemoteException {
		final String mname = "findProducts";
        Trace.entering(_cname, mname);

        // Finds all the objects
        final Collection products = product_dao.selectAll();

        // Transforms domain objects into DTOs
        final Collection productsDTO = transformProductsDTO2DTOs(products);

        Trace.exiting(_cname, mname, new Integer(productsDTO.size()));
        return productsDTO;
	}


	@Override
	public ItemDTO createItem(ItemDTO itemDTO) throws CreateException, CheckException, RemoteException {
		final String mname = "createItem";
        Trace.entering(_cname, mname, itemDTO);

        if (itemDTO == null)
            throw new CreateException("item object is null");
        

        // Transforms DTO into domain object
        Item item = new Item(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setUnitCost(itemDTO.getUnitCost());
        try{
        	item.checkData();
        }catch (CheckException e){
        	if(e.getMessage().equals("Invalid name"))
        		throw new CheckException("Invalid name");
        	if(e.getMessage().equals("Invalid unit cost"))
        		throw new CheckException("Invalid unit cost");
        	if(e.getMessage().equals("Invalid product")){
        		if (itemDTO.getProductId()==null)
                	throw new CheckException("null link");
        		if(itemDTO.getProductId().equals(""))
        			throw new CreateException("empty link name");
        		try {
        			product_dao.findByPrimaryKey(itemDTO.getProductId());
        		} catch (ObjectNotFoundException e1) {
        			throw new CreateException("Product must exist to create an item");
        	    }
        	}
        }
        try {
        	item.setProduct((Product)product_dao.findByPrimaryKey(itemDTO.getProductId()));
		} catch (ObjectNotFoundException e) {
			throw new CreateException("Product must exist to create an item");
	    }
        
        

        // Creates the object
        item_dao.insert(item);

        // Transforms domain object into DTO
        final ItemDTO result = transformItem2DTO(item);

        Trace.exiting(_cname, mname, result);
        return result;
	}


	@Override
	public ItemDTO findItem(String itemId) throws FinderException, CheckException, RemoteException {
		final String mname = "findItem";
        Trace.entering(_cname, mname, itemId);
        
        try{
    	checkId(itemId);
        }catch(CheckException e){
        	throw new ObjectNotFoundException();
        }
        // Finds the object
        final Item item = (Item)item_dao.findByPrimaryKey(itemId);

        // Transforms domain object into DTO
        final ItemDTO itemDTO = transformItem2DTO(item);

        Trace.exiting(_cname, mname, item_dao);
        return itemDTO;
	}

	@Override
	public void deleteItem(String itemId) throws RemoveException, CheckException, RemoteException {
		final String mname = "deleteItem";
        Trace.entering(_cname, mname, itemId);

    	checkId(itemId);

        // Checks if the object exists
        try {
        	item_dao.findByPrimaryKey(itemId);
        } catch (FinderException e) {
            throw new RemoveException("Item must exist to be deleted");
        }

        // Deletes the object
        try {
        	item_dao.remove(itemId);
        } catch (ObjectNotFoundException e) {
            throw new RemoveException("Item must exist to be deleted");
        }
	}

	@Override
	public void updateItem(ItemDTO itemDTO) throws UpdateException, CheckException, RemoteException {
		final String mname = "updateItem";
        Trace.entering(_cname, mname, itemDTO);

        if (itemDTO == null)
            throw new UpdateException("Product object is null");

    	checkId(itemDTO.getId());

    	final Item item;

        // Checks if the object exists
        try {
        	item = (Item)item_dao.findByPrimaryKey(itemDTO.getId());
        } catch (FinderException e) {
            throw new UpdateException("Item must exist to be updated");
        }

        // Transforms DTO into domain object
        item.setName(itemDTO.getName());
        item.setUnitCost(itemDTO.getUnitCost());
        try {
        	item.setProduct((Product)product_dao.findByPrimaryKey(itemDTO.getProductId()));
		} catch (ObjectNotFoundException e1) {
			e1.printStackTrace();
		}

        item.checkData();

        // Updates the object
        try {
        	item_dao.update(item);
        } catch (ObjectNotFoundException e) {
            throw new UpdateException("item must exist to be updated");
        }
		
	}

	@Override
	public Collection findItems() throws FinderException, RemoteException {
		final String mname = "findItems";
        Trace.entering(_cname, mname);

        // Finds all the objects
        final Collection items = item_dao.selectAll();

        // Transforms domain objects into DTOs
        final Collection itemsDTO = transformItems2DTOs(items);

        Trace.exiting(_cname, mname, new Integer(itemsDTO.size()));
        return itemsDTO;
	}


	public final String getUniqueId(final String domainClassName) {
		if(domainClassName.equals("Category"))
			return category_dao.getUniqueId(domainClassName);
		if(domainClassName.equals("Product"))
			return product_dao.getUniqueId(domainClassName);
		if(domainClassName.equals("Item"))
			return item_dao.getUniqueId(domainClassName);
		return null;
    }
    
    
    //private
	private CategoryDTO transformCategory2DTO(Category category) {
		final CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setName(category.getName());
		categoryDTO.setDescription(category.getDescription());
		return categoryDTO;
	}
	
	private Collection transformCategoriesDTO2DTOs(Collection categories) {
		 final Collection categoriesDTO = new ArrayList();
	        for (Iterator iterator = categories.iterator(); iterator.hasNext();) {
	            final Category category = (Category) iterator.next();
	            categoriesDTO.add(transformCategory2DTO(category));
	        }
	        return categoriesDTO;
	}
	
	private ProductDTO transformProduct2DTO(Product product) {
		final ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setDescription(product.getDescription());
		productDTO.setCategoryId(product.getCategory().getId());
		productDTO.setCategoryName(product.getCategory().getName());
		return productDTO;
	}
	
	private Collection transformProductsDTO2DTOs(Collection products) {
		final Collection productsDTO = new ArrayList();
        for (Iterator iterator = products.iterator(); iterator.hasNext();) {
            final Product product = (Product) iterator.next();
            productsDTO.add(transformProduct2DTO(product));
        }
        return productsDTO;
	}
	
	private ItemDTO transformItem2DTO(Item item) {
		final ItemDTO itemDTO = new ItemDTO();
		itemDTO.setId(item.getId());
		itemDTO.setName(item.getName());
		itemDTO.setUnitCost(item.getUnitCost());
		itemDTO.setProductId(item.getProduct().getId());
		itemDTO.setProductName(item.getProduct().getName());
		return itemDTO;
	}
	private Collection transformItems2DTOs(Collection items) {
		final Collection itemsDTO = new ArrayList();
        for (Iterator iterator = items.iterator(); iterator.hasNext();) {
            final Item item = (Item) iterator.next();
            itemsDTO.add(transformItem2DTO(item));
        }
        return itemsDTO;
	}

}
