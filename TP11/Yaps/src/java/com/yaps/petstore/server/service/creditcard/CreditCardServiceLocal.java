package com.yaps.petstore.server.service.creditcard;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.server.domain.CreditCard;

@Remote
@Local
public interface CreditCardServiceLocal {
	 public void verifyCreditCard(final CreditCard creditCard) throws CheckException;

}
