package com.ecom.notification.kafka.order;

import java.math.BigDecimal;
import java.util.List;

import com.ecom.notification.kafka.payment.PaymentMethod;

public record OrderConfirmation(
		
		String orderReference,
		
		BigDecimal amount,
		
		PaymentMethod payMethod,
		
		Customer customer,
		
		List<Product> product
		
		) {

}
