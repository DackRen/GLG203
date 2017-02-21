package com.yaps.petstore.web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yaps.petstore.common.delegate.CommentDelegateFactory;
import com.yaps.petstore.common.delegate.CustomerDelegateFactory;
import com.yaps.petstore.common.dto.CommentDTO;
import com.yaps.petstore.common.dto.CustomerDTO;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.CreateException;
import com.yaps.petstore.common.exception.FinderException;
import com.yaps.petstore.common.logging.Trace;

public class CreateCommentServlet extends AbstractServlet{
    // ======================================
    // =         Entry point method         =
    // ======================================
	protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException{
		final String mname = "service";
        Trace.entering(getCname(), mname);
        //
        String customerId = request.getParameter("customerId");
        HttpSession session = request.getSession();
        if ( session != null ) {
        	CustomerDTO customerDTO = (CustomerDTO)request.getSession().getAttribute("customerDTO");
        	if ( customerDTO != null ) {
        		customerId = customerDTO.getId();
        	}
        }
        
        
        CommentDTO commentDTO = new CommentDTO();
        
        	
        	String title = request.getParameter("title");
            if(title==null||title.equals("")){
            	getServletContext().getRequestDispatcher("/error.jsp?exception=title and text have to be filled").forward(request, response);
            }
            //.isNew()
            else if(request.getSession().isNew()&&customerId==null){
            	getServletContext().getRequestDispatcher("/error.jsp?exception=You should sign on to post a comment").forward(request, response);
            }
            else{
        	try {
				commentDTO.setId(new CommentDelegateFactory().createCommentDelegate().findComments().size()+1+"");
			} catch (FinderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            commentDTO.setTitle(title);
            commentDTO.setText(request.getParameter("text"));
    		commentDTO.setCustomerId(customerId);
    		Date date = new Date();
    		commentDTO.setDate(date);
    		
			try {
				commentDTO = new CommentDelegateFactory().createCommentDelegate().createComment(commentDTO);
			} catch (CreateException | CheckException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


        // puts the customerId and password into the request
        request.setAttribute("id", customerId);
        
        // Goes to the create customer page passing the request
        getServletContext().getRequestDispatcher("/searchcomments").forward(request, response);
            }
    }

}
