package com.yaps.petstore.server.service.creditcard;

import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.server.domain.CreditCard;


public interface CreditCardServiceLocal {
	 public void verifyCreditCard(final CreditCard creditCard) throws CheckException;

}
