package com.ecom.kafka.producer;

import java.math.BigDecimal;
import java.util.List;

import com.ecom.customer.response.CustomerResponse;
import com.ecom.order.model.PaymentMethod;
import com.ecom.productpurchase.respponse.PurchaseResponse;

public record OrderConfirmation(
		
		String orderReference,
		
		BigDecimal totalAmount,
		
		PaymentMethod patment,
		
		CustomerResponse customer,
		
		List<PurchaseResponse> product
		
		) {

}
