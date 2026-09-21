package com.ecom.handler;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.errorresponse.ErrorResponse;
import com.ecom.exception.CustomerNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> ifCustomerNotFound(CustomerNotFoundException cnfe){
		return new ResponseEntity<String>(cnfe.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> ifMethodArgumentNotValid(MethodArgumentNotValidException manv){
		var errors = new HashMap<String,String>();
		manv.getBindingResult().getAllErrors().
		     forEach(error ->{
					var field = ((FieldError) error).getField();
					var defaultMessage = error.getDefaultMessage();
					errors.put(field, defaultMessage);
				});
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
	}
}
