package com.blogapp.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseStatus
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> validationHandler(MethodArgumentNotValidException ex){
		Map<String, String> validatonErrors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			validatonErrors.put(error.getField(), error.getDefaultMessage());
		});
		return validatonErrors;
	}
	
	

}
