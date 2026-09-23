package com.ecom.order.payment.request;

import java.math.BigDecimal;

import com.ecom.customer.response.CustomerResponse;
import com.ecom.order.model.PaymentMethod;

public record PaymentRequest(
		
		BigDecimal amount,
		
		PaymentMethod payMethod,
		
		Integer orderId,
		
		String orderReference,
		
		CustomerResponse customer
		
		) {

}
