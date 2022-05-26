package com.ibm.creditcard.mapper;

import com.ibm.creditcard.model.dto.CreditCardDTO;
import com.ibm.creditcard.model.entities.CreditCard;

public class CreditCardMapper {
	
	public static CreditCardDTO mapCreditCardDTO(CreditCard creditCard) {
		CreditCardDTO creditCardDTO = new CreditCardDTO();
		creditCardDTO.setId(creditCard.getId());
		creditCardDTO.setSuggestedCard(creditCard.getCreditCardSuggested());
		creditCardDTO.setAgeFrom(creditCard.getAgeFrom());
		creditCardDTO.setAgeTo(creditCard.getAgeTo());
		creditCardDTO.setSalaryFrom(creditCard.getMonthlySalaryFrom());
		creditCardDTO.setSalaryTo(creditCard.getMonthlySalaryTo());
		return creditCardDTO;
		
	}

}
