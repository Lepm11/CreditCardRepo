package com.ibm.creditcard.repositories;

import com.ibm.creditcard.model.entities.CreditCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCard, Long>
{

	@Query("select c from CreditCard c where c.yourPassion = lower(?1) and (?2 BETWEEN c.monthlySalaryFrom AND monthly_salary_to) AND (?3 BETWEEN age_from AND age_to)")
    public List<CreditCard> buscarTarjetaSugerida(String yourPassion, Double monthlySalary,Integer age );

	// select your_passion from creditcards.creditcarduno where your_passion = 'Shopping';
	@Query("select c from CreditCard c where c.yourPassion = lower(?1)")
	public List<CreditCard> buscarPassion(String yourPassion);
	
}
