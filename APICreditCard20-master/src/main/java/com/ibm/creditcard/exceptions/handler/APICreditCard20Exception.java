package com.ibm.creditcard.exceptions.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ibm.creditcard.exceptions.BadAgeException;
import com.ibm.creditcard.exceptions.BadPassionException;
import com.ibm.creditcard.exceptions.BadRequestException;
import com.ibm.creditcard.exceptions.BadSalaryException;

@RestControllerAdvice
public class APICreditCard20Exception {
	
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handleNotFound(BadRequestException e) {
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", e.getMessage());
		return response;
	}
	
	@ExceptionHandler(BadPassionException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handleNotFound(BadPassionException e) {
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", e.getMessage());
		return response;
	}
	
	@ExceptionHandler(BadAgeException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handleNotFound(BadAgeException e) {
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", e.getMessage());
		return response;
	}
	
	@ExceptionHandler(BadSalaryException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handleNotFound(BadSalaryException e) {
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", e.getMessage());
		return response;
	}
	
	
}
