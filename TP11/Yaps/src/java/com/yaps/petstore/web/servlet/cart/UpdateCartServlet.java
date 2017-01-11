package com.yaps.petstore.web.servlet.cart;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yaps.petstore.common.delegate.OrderDelegate;
import com.yaps.petstore.common.delegate.ShoppingCartDelegate;
import com.yaps.petstore.common.dto.CustomerDTO;
import com.yaps.petstore.common.logging.Trace;
import com.yaps.petstore.web.servlet.AbstractServlet;

public class UpdateCartServlet extends AbstractServlet{
	
	// ======================================
    // =         Entry point method         =
    // ======================================
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String mname = "service";
        Trace.entering(getCname(), mname);

        try {
            // Update the itemId into the Shopping Cart
        	ShoppingCartDelegate delegate = new ShoppingCartDelegate(request.getSession().getId());
            delegate.updateItemQuantity(request.getParameter("itemId"),Integer.parseInt(request.getParameter("quantity")));

            getServletContext().getRequestDispatcher("/viewcart").forward(request, response);

        } catch (RemoteException e) {
            Trace.throwing(getCname(), mname, e);
            getServletContext().getRequestDispatcher("/error.jsp?exception=Cannot add an item to the shopping cart").forward(request, response);
        }
    }
}
