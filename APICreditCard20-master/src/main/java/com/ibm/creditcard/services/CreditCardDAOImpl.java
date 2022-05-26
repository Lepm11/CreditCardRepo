package com.ibm.creditcard.services;

import com.ibm.creditcard.exceptions.BadAgeException;
import com.ibm.creditcard.exceptions.BadPassionException;
import com.ibm.creditcard.exceptions.BadRequestException;
import com.ibm.creditcard.exceptions.BadSalaryException;
import com.ibm.creditcard.model.entities.CreditCard;
import com.ibm.creditcard.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardDAOImpl implements CreditCardDAO
{
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Override
    public CreditCard save(CreditCard entidad)
    {
        return creditCardRepository.save(entidad);
    }
    
    /**
     *  función para buscar la tarjeta sugerida del usuario.
     *  @throws BadAgeException en el caso de que el rango de edad del usuario no sea correcto.
     *  @throws BadSalaryException en el caso de que el rango salarial del usuario no sea correcto.
     *  @throws BadPassionException en el caso de que no exista la passion solicitada.
     *  @throws BadRequestException en el caso de que no exista ninguna tarjeta sugerida con los datos enviados
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CreditCard> buscarTarjetaSugerida(String yourPassion, Double monthlySalary,Integer age )
    {
    	
    	if(age < 18 || age > 75) {
			throw new BadAgeException(String.format("El rango de edad debe ser entre 18 a 75 años")); 
    	}
    	if(monthlySalary < 7000) {
			throw new BadSalaryException(String.format("El rango salariar debe ser mayor o igual a 7,000"));    		
    	}
    	
    	if(creditCardRepository.buscarPassion(yourPassion).isEmpty()) {
			throw new BadPassionException(String.format("No existe esa passion"));    		
    	}else {
    		if(!creditCardRepository.buscarTarjetaSugerida(yourPassion, monthlySalary,age ).isEmpty()) {
        		return  Optional.of(creditCardRepository.buscarTarjetaSugerida(yourPassion, monthlySalary,age ).get(0));
        	}
        	else {
    			throw new BadRequestException(String.format("No existen sugerencias para los datos ingresados, revisa los datos"));
        	}
			
		}
    	
    	
    	
    	
    }
}
