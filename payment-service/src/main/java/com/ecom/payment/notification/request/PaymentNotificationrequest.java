package com.ecom.payment.notification.request;

import java.math.BigDecimal;

import com.ecom.payment.model.PaymentMethod;

public record PaymentNotificationrequest(
		
		String orderReference,
		
		BigDecimal amount,
		
		PaymentMethod payMethod,
		
		String customerFirstName,
		
		String customerLastName,
		
		String customerEmail
		
		) {

}
