package com.yaps.petstore.server.service.order;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.yaps.petstore.common.dto.OrderDTO;
import com.yaps.petstore.common.dto.OrderLineDTO;
import com.yaps.petstore.common.exception.*;

import com.yaps.petstore.common.locator.ServiceLocator;
import com.yaps.petstore.common.logging.Trace;
import com.yaps.petstore.server.domain.customer.Customer;
import com.yaps.petstore.server.domain.customer.CustomerDAO;
import com.yaps.petstore.server.domain.item.Item;
import com.yaps.petstore.server.domain.item.ItemDAO;
import com.yaps.petstore.server.domain.order.Order;
import com.yaps.petstore.server.domain.order.OrderDAO;
import com.yaps.petstore.server.domain.orderline.OrderLine;
import com.yaps.petstore.server.domain.orderline.OrderLineDAO;
import com.yaps.petstore.server.service.AbstractRemoteService;

import com.yaps.petstore.server.service.creditcard.CreditCardServiceBean;
import com.yaps.petstore.server.service.creditcard.CreditCardServiceLocal;
import com.yaps.petstore.server.service.creditcard.CreditCardServiceLocalHome;



@Stateless (name="OrderSB")
public class OrderServiceBean extends AbstractRemoteService implements OrderService{

    private static final OrderDAO _orderDAO = new OrderDAO();
    private static final OrderLineDAO _orderLineDAO = new OrderLineDAO();
    private static final CustomerDAO _customerDAO = new CustomerDAO();
    private static final ItemDAO _itemDAO = new ItemDAO();
    
    @EJB
    CreditCardServiceLocal _creditCardServiceLocal;

    // ======================================
    // =            Constructors            =
    // ======================================
    public OrderServiceBean(){
    }

    // ======================================
    // =           Business methods         =
    // ======================================
    
	@Override
	public String createOrder(String customerId, Map shoppingCart) throws CreateException, CheckException {
		final String mname = "createOrder";
        Trace.entering(getCname(), mname, customerId);

        if (customerId == null)
            throw new CreateException("Customer object is null");

        // Finds the customer
        Customer customer = null;
        try {
            customer = (Customer)_customerDAO.findByPrimaryKey(customerId);
        } catch (FinderException e) {
            throw new CreateException("Customer must exist to create an order");
        }

        // Transforms Order DTO into domain object
        final Order order = new Order(  customer.getFirstname(), customer.getLastname(),
        		customer.getStreet1(), customer.getCity(),
        		customer.getZipcode(), customer.getCountry(), customer);
        order.setStreet2(customer.getStreet2());
        order.setState(customer.getState());
        order.setCreditCardExpiryDate(customer.getCreditCardExpiryDate());
        order.setCreditCardNumber(customer.getCreditCardNumber());
        order.setCreditCardType(customer.getCreditCardType());

        
        _creditCardServiceLocal.verifyCreditCard(order.getCreditCard());

        // Creates the order
        _orderDAO.insert(order);

        // Creates all the orderLines linked with the order
        Collection orderLines = new ArrayList();
        for(Object iterator : shoppingCart.keySet()) {
        	// Finds the item
            Item item = null;
            try {
                item = (Item)_itemDAO.findByPrimaryKey((String)iterator);
            } catch (FinderException e) {
                throw new CreateException("Item must exist to create an order line");
            }
            // Transforms OrderLine DTO into domain object
            final OrderLine orderLine = new OrderLine((int)shoppingCart.get(iterator), item.getUnitCost(), order, item);
            // Creates the order line
            _orderLineDAO.insert(orderLine);
            orderLines.add(orderLine);
        }
        // Sets orderLines into the order
        order.setOrderLines(orderLines);
        
        return order.getId();
	}
	
    public OrderDTO createOrder(final OrderDTO orderDTO) throws CreateException, CheckException {
        final String mname = "createOrder";
        Trace.entering(getCname(), mname, orderDTO);

        if (orderDTO == null)
            throw new CreateException("Order object is null");

        if (orderDTO.getOrderLines() == null || orderDTO.getOrderLines().size() <= 0)
            throw new CheckException("There are no order lines");

        // Finds the customer
        Customer customer = null;
        try {
            customer = (Customer)_customerDAO.findByPrimaryKey(orderDTO.getCustomerId());
        } catch (FinderException e) {
            throw new CreateException("Customer must exist to create an order");
        }

        // Transforms Order DTO into domain object
        final Order order = new Order(  orderDTO.getFirstname(), orderDTO.getLastname(),
                                        orderDTO.getStreet1(), orderDTO.getCity(),
                                        orderDTO.getZipcode(), orderDTO.getCountry(), customer);
        order.setStreet2(orderDTO.getStreet2());
        order.setState(orderDTO.getState());
        order.setCreditCardExpiryDate(orderDTO.getCreditCardExpiryDate());
        order.setCreditCardNumber(orderDTO.getCreditCardNumber());
        order.setCreditCardType(orderDTO.getCreditCardType());

        _creditCardServiceLocal.verifyCreditCard(order.getCreditCard());
        

        // Creates the order
        _orderDAO.insert(order);

        // Creates all the orderLines linked with the order
        Collection orderLines = new ArrayList();
        for (Iterator iterator = orderDTO.getOrderLines().iterator(); iterator.hasNext();) {
            final OrderLineDTO orderLineDTO = (OrderLineDTO) iterator.next();
            // Finds the item
            Item item = null;
            try {
                item = (Item)_itemDAO.findByPrimaryKey(orderLineDTO.getItemId());
            } catch (FinderException e) {
                throw new CreateException("Item must exist to create an order line");
            }
            // Transforms OrderLine DTO into domain object
            final OrderLine orderLine = new OrderLine(orderLineDTO.getQuantity(), orderLineDTO.getUnitCost(), order, item);
            // Creates the order line
            _orderLineDAO.insert(orderLine);
            orderLines.add(orderLine);
        }
        // Sets orderLines into the order
        order.setOrderLines(orderLines);

        // Transforms domain object into DTO
        final OrderDTO result = transformOrder2DTO(order);
        return result;
    }

    public OrderDTO findOrder(final String orderId) throws FinderException, CheckException {
        final String mname = "findOrder";
        Trace.entering(getCname(), mname, orderId);
        
        if(orderId==null||orderId.equals(""))
        	throw new CheckException("");

        // Finds the object
        Order order = (Order) _orderDAO.findByPrimaryKey(orderId);

        // Retreives the data for the customer and sets it
        Customer customer = (Customer) _customerDAO.findByPrimaryKey(order.getCustomer().getId());
        order.setCustomer(customer);

        // Retreives the data for all the order lines
        final Collection orderLines = _orderLineDAO.findAll(orderId);
        order.setOrderLines(orderLines);

        // Transforms domain object into DTO
        final OrderDTO orderDTO = transformOrder2DTO(order);

        Trace.exiting(getCname(), mname, orderDTO);
        return orderDTO;
    }

    public void deleteOrder(final String orderId) throws RemoveException, CheckException {
        final String mname = "deleteOrder";
        Trace.entering(getCname(), mname, orderId);

        checkId(orderId);

        // Checks if the object exists
        try {
        	_orderDAO.findByPrimaryKey(orderId);
        } catch (FinderException e) {
            throw new RemoveException("Order must exist to be deleted");
        }

        // Deletes the object
        try {
        	_orderDAO.remove(orderId);
        } catch (ObjectNotFoundException e) {
            throw new RemoveException("Customer must exist to be deleted");
        }
    }

    // ======================================
    // =          Private Methods           =
    // ======================================
    private OrderDTO transformOrder2DTO(final Order order) {
        final OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCity(order.getCity());
        orderDTO.setCountry(order.getCountry());
        orderDTO.setCreditCardExpiryDate(order.getCreditCardExpiryDate());
        orderDTO.setCreditCardNumber(order.getCreditCardNumber());
        orderDTO.setCreditCardType(order.getCreditCardType());
        orderDTO.setCustomerId(order.getCustomer().getId());
        orderDTO.setFirstname(order.getFirstname());
        orderDTO.setLastname(order.getLastname());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setState(order.getState());
        orderDTO.setStreet1(order.getStreet1());
        orderDTO.setStreet2(order.getStreet2());
        orderDTO.setZipcode(order.getZipcode());
        // Transforms all the order lines
        orderDTO.setOrderLines(transformOrderLines2DTOs(order.getOrderLines()));
        return orderDTO;
    }

    private Collection transformOrderLines2DTOs(final Collection orderLines) {
        final Collection orderLinesDTO = new ArrayList();
        OrderLineDTO orderLineDTO;
        for (Iterator iterator = orderLines.iterator(); iterator.hasNext();) {
            final OrderLine orderLine = (OrderLine) iterator.next();
            orderLineDTO = new OrderLineDTO();
            orderLineDTO.setItemId(orderLine.getItem().getId());
            orderLineDTO.setItemName(orderLine.getItem().getName());
            orderLineDTO.setQuantity(orderLine.getQuantity());
            orderLineDTO.setUnitCost(orderLine.getUnitCost());
            orderLinesDTO.add(orderLineDTO);
        }
        return orderLinesDTO;
    }

    /**
     * This method returns a unique identifer generated by the system. 
     *
     * @param domainClassName name of a domain class (Order, OrderLine, ...)
     * @return a unique identifer
     */
    public final String getUniqueId(final String domainClassName) {
        return _orderDAO.getUniqueId(domainClassName);
    }
    
}
