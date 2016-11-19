package com.yaps.petstore.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yaps.petstore.common.delegate.CustomerDelegate;
import com.yaps.petstore.common.dto.CustomerDTO;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.CreateException;

public class CreateCustomerServlet extends HttpServlet {
//	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
//		try {
//			doPost(req, res);
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    res.setContentType("text/html");
//	    PrintWriter out = res.getWriter();
//	    out.print("<HEAD><TITLE>");
//	    out.print("A server-side strategy");
//	    out.print("</TITLE></HEAD><BODY>");
//	    out.print("<h1>Servlets Rule! ");
//	    out.print("</h1></BODY>");
//	    out.close();
//	  }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		String id = request.getParameter("id");
		String firstname = request.getParameter("lastname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String street1 = request.getParameter("street1");		
		String street2 = request.getParameter("street2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		String country = request.getParameter("country");
		String creditCardType = request.getParameter("creditCardType");
		String creditCardNumber = request.getParameter("creditCardNumber");
		String creditCardExpiryDate = request.getParameter("creditCardExpiryDate");
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(id);
		customerDTO.setFirstname(firstname);
		customerDTO.setLastname(lastname);
		customerDTO.setEmail(email);
		customerDTO.setTelephone(telephone);
		customerDTO.setStreet1(street1);
		customerDTO.setStreet2(street2);
		customerDTO.setCity(city);
		customerDTO.setState(state);
		customerDTO.setZipcode(zipcode);
		customerDTO.setCountry(country);
		customerDTO.setCreditCardType(creditCardType);
		customerDTO.setCreditCardNumber(creditCardNumber);
		customerDTO.setCreditCardExpiryDate(creditCardExpiryDate);
		
		try {
			CustomerDelegate.createCustomer(customerDTO);
			show(request, response);
		} catch (CreateException | CheckException e) {
			ErrorServlet es = new ErrorServlet();
			es.service(request, response);
			e.printStackTrace();
		}
		
    }
	private void show(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Customer Created!!</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1><center>Customer Created!!</center></h1>");
        out.println("</body>");
        out.println("</html>");
        out.close();
	}
	

}
