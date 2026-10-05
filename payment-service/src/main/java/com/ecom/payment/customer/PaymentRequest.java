package com.ecom.payment.customer;

import java.math.BigDecimal;

import com.ecom.payment.model.PaymentMethod;

public record PaymentRequest(
		
		Integer id,
		
		PaymentMethod payMethod,
		
		BigDecimal amount,
		
		Integer orderId,
		
		String orderReference,
		
		Customer customer
		
		) {

}
