package com.yaps.petstore.server.service.creditcard;

import org.dom4j.Document;

import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.server.domain.CreditCard;

public class CreditCardService {
	public void verifyCreditCard(CreditCard creditCard) throws CheckException{
		Document document = HTTPSender.send(creditCard.toXML());
		String status = document.selectSingleNode("/CreditCard").valueOf("@Status");
//		if(status==null||status.equals("Validation Exception"))
		if(!status.equals("Valid"))
			throw new CheckException(status);
	}
}
