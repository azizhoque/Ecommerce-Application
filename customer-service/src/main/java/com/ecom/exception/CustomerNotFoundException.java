package com.ecom.exception;

public class CustomerNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerNotFoundException(String message) {
		super(message);
	}

	
}
