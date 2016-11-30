package com.barkbank.verifier;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * Servlet implementation class VerifyCreditCardServlet
 */

public final class VerifyCreditCardServlet extends HttpServlet {
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Document doucment = null;
		Element root = null;
		try {
			doucment = DocumentHelper.parseText(request.getParameter("param"));
			root = doucment.getRootElement();
			root.addAttribute("Status",VerifyCreditCard(doucment));
		} catch (DocumentException e1) {
			e1.printStackTrace();
		} catch (Exception e2){
			if(root!=null)
				root.addAttribute("Status","Validation Exception");
		}
		OutputFormat format = OutputFormat.createCompactFormat();
	    format.setEncoding("UTF-8");
		XMLWriter writer;
		try {
		    writer = new XMLWriter(response.getOutputStream(), format);
		    //resDoc为通过http返回的xml文件
		    writer.write(doucment);
		    writer.flush();
		    if (null != writer) {
		        writer.close();
		    }
		} catch (Exception e) {
		    	e.printStackTrace();
		    }
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
    private String VerifyCreditCard(Document document){    	
    	Node cardNumber = document.selectSingleNode("/CreditCard/CardNumber");
    	Node cardType = document.selectSingleNode("/CreditCard/CardType");
    	Node expiryDate = document.selectSingleNode("/CreditCard/ExpiryDate");
    	
		return VerificationAlgorithm.verify(cardNumber.getText(), cardType.getText(), expiryDate.valueOf("@Year"), expiryDate.valueOf("@Month"));
    }
}
