package com.ecom.order.handler;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.order.errorresponse.ErrorResponse;
import com.ecom.order.exception.BusinessException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<String> ifOrderNoFound(BusinessException bex) {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> ifMethodArgumentNotValid(MethodArgumentNotValidException manv) {
		var errors = new HashMap<String, String>();
		manv.getBindingResult().getAllErrors().forEach(error -> {
			var field = ((FieldError) error).getField();
			var defaultMessage = error.getDefaultMessage();
			errors.put(field, defaultMessage);
		});
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> ifProductQuantityNotAvailable(EntityNotFoundException ex) {
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
}
