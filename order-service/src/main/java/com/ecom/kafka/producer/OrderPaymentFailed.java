package com.ecom.kafka.producer;

import java.math.BigDecimal;

import com.ecom.customer.response.CustomerResponse;
import com.ecom.order.model.PaymentMethod;

public record OrderPaymentFailed(
		
        String orderReference,
        
        BigDecimal amount,
        
        PaymentMethod paymentMethod,
        
        CustomerResponse customer,
        
        String reason
		
		) {

}
