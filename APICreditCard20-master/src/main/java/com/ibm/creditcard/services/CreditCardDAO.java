package com.ibm.creditcard.services;

import com.ibm.creditcard.model.entities.CreditCard;

import java.util.Optional;

public interface CreditCardDAO
{
    public CreditCard save(CreditCard entidad);
    public Optional<CreditCard> buscarTarjetaSugerida(String yourPassion, Double monthlySalary,Integer age);
}
