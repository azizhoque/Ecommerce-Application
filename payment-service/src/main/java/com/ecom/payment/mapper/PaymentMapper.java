package com.ecom.payment.mapper;

import org.springframework.stereotype.Service;

import com.ecom.payment.customer.PaymentRequest;
import com.ecom.payment.model.Payment;

@Service
public class PaymentMapper {

	public Payment toPayment(PaymentRequest request) {
		return Payment.builder().
				id(request.id()).
				OrderId(request.orderId()).
				payMethod(request.payMethod()).
				amount(request.amount()).
				build();
	}
}
