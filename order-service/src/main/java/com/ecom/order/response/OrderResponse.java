package com.ecom.order.response;

import java.math.BigDecimal;

import com.ecom.order.model.PaymentMethod;

public record OrderResponse(
		
		Integer id,
		
		String reference,
		
		BigDecimal amount,
		
		PaymentMethod payment,
		
		String customerId
		
		) {

}
