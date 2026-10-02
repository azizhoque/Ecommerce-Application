package com.ecom.payment.response;

public record PaymentFailedResponse(
		
		Integer paymentId,
		
	    Integer orderId,
	    
	    String orderReference,
	    
	    String reason
		
		) {

}
