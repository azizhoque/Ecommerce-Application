package com.ecom.notification.kafka.payment;

import java.math.BigDecimal;

public record PaymentConfirmation(
		
		String orderReference,
		
		BigDecimal amount,
		
		PaymentMethod payMethod,
		
		String customerFirstName,
		
		String customerLastName,
		
		String customerEmail
		
		) {

}
