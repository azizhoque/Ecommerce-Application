package com.ecom.payment.producer;

import java.math.BigDecimal;

import com.ecom.payment.model.PaymentMethod;
import com.ecom.payment.model.PaymentStatus;

public record PaymentSuccessNotificationRequest(
		
		String orderReference,
		
		BigDecimal amount,
		
		PaymentMethod payMethod,
		
		String customerFirstName,
		
		String customerLastName,
		
		String customerEmail,
		
		PaymentStatus status
		
		) {

}
