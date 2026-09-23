package com.ecom.payment.service;

import com.ecom.payment.customer.PaymentRequest;
import com.ecom.payment.response.PaymentResponse;

public interface IPaymentService {

	public PaymentResponse createPayment(PaymentRequest request);

}
