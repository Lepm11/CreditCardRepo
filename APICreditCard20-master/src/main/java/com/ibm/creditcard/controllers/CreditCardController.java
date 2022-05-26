package com.ibm.creditcard.controllers;

import com.ibm.creditcard.exceptions.BadRequestException;
import com.ibm.creditcard.mapper.CreditCardMapper;
import com.ibm.creditcard.model.dto.CreditCardDTO;
import com.ibm.creditcard.model.entities.CreditCard;
import com.ibm.creditcard.services.CreditCardDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/creditCard")
public class CreditCardController
{
    private final static Logger logger = LoggerFactory.getLogger(CreditCardController.class);

    @Autowired
    private CircuitBreakerFactory circuitBreaker;

    @Autowired
    private CreditCardDAO creditCardDao;
    /**
     * 
     * @param creditCard
     * @param result
     * @return
     * @author Abel&Enrique
     */
    @PostMapping("/altaProductoTarjetaCredito")
    public ResponseEntity<?>save(@RequestBody CreditCard creditCard, BindingResult result)
    {
        CreditCard creditCard1 = creditCardDao.save(creditCard);

        logger.info("Tarjeta creada");

        return new ResponseEntity<CreditCard>(creditCard1, HttpStatus.CREATED);

    }

    /**
     *  Endpoint para mostrar la tarjeta sugerida con respecto a los parametros ingresados
     * @param yourPassion, parametro de tipo string que indica la passion del usuario.
     * @param monthlySalary,parametro tipo Double que indica el salario mensual del usuario.
     * @param age, parametro de tipo Integer que indica la edad del usuario
     * @return retorna la tarjeta sugeridad según los datos ingresados.
     * @throws BadRequestException en caso de no encontrar alguna coincidencia de tarjeta con los datos enviados.
     * @author Abel&Enrique
     * 
     * 
     */
    @GetMapping("/sugerencia")
    public ResponseEntity<?> buscarTarjetaSugerida(@RequestParam(name="passion") String yourPassion, @RequestParam(name="salary") Double monthlySalary, @RequestParam Integer age)
    {
        return circuitBreaker.create("tarjetas")
                .run(()->{
                    Optional<CreditCard> creditCard = creditCardDao.buscarTarjetaSugerida(yourPassion, monthlySalary,age);

                    if (creditCard.isEmpty()) {

            			throw new BadRequestException(String.format("No existen sugerencias para los datos ingresados, revisa los datos"));
            		}
                 
                    return new ResponseEntity<Optional<CreditCard>>(creditCard, HttpStatus.OK);
                }, e-> metodoAlternativo(yourPassion,monthlySalary,age,e));

    }
    
    /**
     *  Ednpoint para
     * @param yourPassion, parametro de tipo string que indica la passion del usuario.
     * @param monthlySalary,parametro tipo Double que indica el salario mensual del usuario.
     * @param age, parametro de tipo Integer que indica la edad del usuario
     * @return retorna la tarjeta sugeridad según los datos ingresados.
     * @throws BadRequestException en caso de no encontrar alguna coincidencia de tarjeta con los datos enviados.
     * @author Abel&Enrique
     * 
     */
    public ResponseEntity<?> metodoAlternativo(String yourPassion, Double monthlySalary, Integer age, Throwable e)
    {
        logger.info(e.getMessage());

        HashMap<String, String> sugerencia = new HashMap<String, String>();
        Optional<CreditCard> creditCard = creditCardDao.buscarTarjetaSugerida(yourPassion, monthlySalary,age);
        if (creditCard.isEmpty()) {

			throw new BadRequestException(String.format("No existen sugerencias para los datos ingresados, revisa los datos"));
			
		}
        sugerencia.put("passion",yourPassion);
        sugerencia.put("monthlySalary",monthlySalary.toString());
        sugerencia.put("age",age.toString());

        return new ResponseEntity<>(sugerencia,HttpStatus.REQUEST_TIMEOUT);
    }
    
    /**
     * Endpoint para mostrar la tarjeta sugerida DTO, unicamente mostrando los datos de interés.
     * @param yourPassion, parametro de tipo string que indica la passion del usuario.
     * @param monthlySalary,parametro tipo Double que indica el salario mensual del usuario.
     * @param age, parametro de tipo Integer que indica la edad del usuario
     * @return retorna la tarjeta sugeridad según los datos ingresados.
     * @throws BadRequestException en caso de no encontrar alguna coincidencia de tarjeta con los datos enviados.
     * @author Abel&Enrique
     * 
     */
    @GetMapping("/sugerencia/dto")
    public ResponseEntity<?> buscarTarjetaSugeridaDTO(@RequestParam(name="passion") String yourPassion, @RequestParam(name="salary") Double monthlySalary, @RequestParam Integer age)
    {
    	
    	return circuitBreaker.create("tarjetas")
                .run(()->{
                	Optional<CreditCard> creditCard = creditCardDao.buscarTarjetaSugerida(yourPassion, monthlySalary,age);
                    if (creditCard.isEmpty()) {

            			throw new BadRequestException(String.format("No existen sugerencias para los datos ingresados, revisa los datos"));
            			
            		}
                    List<CreditCardDTO> creditCardDto = creditCard.stream().map(CreditCardMapper::mapCreditCardDTO).collect(Collectors.toList());
                    creditCardDto.get(0).setUserAge(age);
                    creditCardDto.get(0).setUserPassion(yourPassion);
                    creditCardDto.get(0).setUserSalary(monthlySalary);
                    HashMap<String, String> sugerencia = new HashMap<String, String>();
                    sugerencia.put("SUGERENCIA",creditCardDto.toString());
                    return new ResponseEntity<List<CreditCardDTO>>(creditCardDto, HttpStatus.OK);
                    //return ResponseEntity.ok(creditCardDto.toString());
                }, e-> metodoAlternativo(yourPassion,monthlySalary,age,e));
    	
    }
}














