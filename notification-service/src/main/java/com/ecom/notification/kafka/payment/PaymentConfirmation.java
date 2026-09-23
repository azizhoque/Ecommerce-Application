package com.ecom.notification.kafka.payment;

import java.math.BigDecimal;

public record PaymentConfirmation(
		
		String orderReference,
		
		BigDecimal amount,
		
		String customerFirstName,
		
		String customerLastName,
		
		String customerEmail
		
		) {

}
