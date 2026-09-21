package com.ecom.product.exception;

public class ProductPurchaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProductPurchaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductPurchaseException(String message) {
		super(message);
	}
	
	

}
