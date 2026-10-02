package com.ecom.kafka.producer;

import java.math.BigDecimal;

import com.ecom.order.model.PaymentMethod;

public record OrderPaymentFailed(
		
		Integer orderId,
		
        String orderReference,
        
        BigDecimal amount,
        
        PaymentMethod paymentMethod,
        
        String customerFirstName,
        
        String customerLastName,
        
        String customerEmail,
        
        String reason
		
		) {

}
