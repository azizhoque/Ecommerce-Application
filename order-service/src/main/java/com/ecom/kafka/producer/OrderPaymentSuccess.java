package com.ecom.kafka.producer;

import java.math.BigDecimal;

public record OrderPaymentSuccess(
		
		Integer paymentId,
        Integer orderId,
        String orderReference,
        BigDecimal amount
		
		) {

}
