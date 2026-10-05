package com.ecom.kafka.producer;

import java.math.BigDecimal;
import java.util.List;

import com.ecom.customer.response.CustomerResponse;
import com.ecom.order.model.PaymentMethod;
import com.ecom.productpurchase.respponse.PurchaseResponse;

public record OrderConfirmationNotification(

		String orderReference, 
		BigDecimal totalAmount, 
		PaymentMethod paymentMethod, 
		CustomerResponse customer,
		List<PurchaseResponse> products
		) {

}
