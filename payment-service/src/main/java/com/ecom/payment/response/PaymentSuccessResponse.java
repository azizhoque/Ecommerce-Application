package com.ecom.payment.response;

import java.math.BigDecimal;

public record PaymentSuccessResponse(
		
        Integer paymentId,
        
        Integer orderId,
        
        String orderReference,
        
        BigDecimal amount
		
		) {

}
