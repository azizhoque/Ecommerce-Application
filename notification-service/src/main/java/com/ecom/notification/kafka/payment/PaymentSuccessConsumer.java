package com.ecom.notification.kafka.payment;

import java.math.BigDecimal;

public record PaymentSuccessConsumer(
		
		String orderReference,
		
		BigDecimal amount,
		
		PaymentMethod payMethod,
		
		String customerFirstName,
		
		String customerLastName,
		
		String customerEmail,
		
		PaymentStatus status
		
		) {

}
