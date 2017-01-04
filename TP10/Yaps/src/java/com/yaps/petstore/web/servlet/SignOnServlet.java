package com.yaps.petstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yaps.petstore.common.delegate.CustomerDelegateFactory;
import com.yaps.petstore.common.dto.CustomerDTO;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.FinderException;
import com.yaps.petstore.common.logging.Trace;

public class SignOnServlet extends AbstractServlet{
	
    // ======================================
    // =         Entry point method         =
    // ======================================
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String mname = "service";
        Trace.entering(getCname(), mname);
        
        String customerId = request.getParameter("customerId");
        String password = request.getParameter("password");
        
        CustomerDTO customerDTO = null;
        try {
        	customerDTO = new CustomerDelegateFactory().createCustomerDelegate().authenticate(customerId, password);
		} catch (FinderException | CheckException e) {
			e.printStackTrace();
		}
        
        request.getSession().setAttribute("customerDTO", customerDTO);
        // Goes to the index page passing the request
        getServletContext().getRequestDispatcher("/signoff.jsp").forward(request, response);
    }

}
