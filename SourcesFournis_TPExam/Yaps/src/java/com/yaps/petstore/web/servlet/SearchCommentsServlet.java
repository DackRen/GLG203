package com.yaps.petstore.web.servlet;

import com.yaps.petstore.common.delegate.CommentDelegateFactory;
import com.yaps.petstore.common.dto.CommentDTO;
import com.yaps.petstore.common.dto.CustomerDTO;
import com.yaps.petstore.common.exception.ObjectNotFoundException;
import com.yaps.petstore.common.logging.Trace;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

/**
 * This servlet returns the list of searched items.
 */
public class SearchCommentsServlet extends AbstractServlet {

    // ======================================
    // =         Entry point method         =
    // ======================================
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String mname = "service";
        Trace.entering(getCname(), mname);

        final Collection commentsDTO;
        String customerId = request.getParameter("customerId"); // For HttpUnit test!!
        HttpSession session = request.getSession();
        if ( session != null ) {
        	CustomerDTO customerDTO = (CustomerDTO)request.getSession().getAttribute("customerDTO");
        	if ( customerDTO != null ) 
        		customerId = customerDTO.getId();
        }

        try {
            // Search the comments 
        	if ( customerId != null && ! "".equals(customerId) )
        		commentsDTO = new CommentDelegateFactory().createCommentDelegate().findCommentsFromCustomer(customerId);
        	else {
        		commentsDTO = new CommentDelegateFactory().createCommentDelegate().findComments();
        		customerId = null;
        	}
            // puts the list of comments and the customerId into the request
            request.setAttribute("commentsDTO", commentsDTO);
            request.setAttribute("customerId", customerId);

            // Goes to the comments page passing the request
            getServletContext().getRequestDispatcher("/comments.jsp").forward(request, response);

        } catch (ObjectNotFoundException e) {
            getServletContext().getRequestDispatcher("/error.jsp?exception=No comments found for customer " + customerId + " (" +e +")").forward(request, response);
        } catch (Exception e) {
            Trace.throwing(getCname(), mname, e);
            getServletContext().getRequestDispatcher("/error.jsp?exception=Cannot search for comments" + e).forward(request, response);
        }
    }
}
