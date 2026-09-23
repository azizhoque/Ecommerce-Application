package com.ecom.payment.service;

import com.ecom.payment.customer.PaymentRequest;

public interface IPaymentService {

	public Integer createPayment(PaymentRequest request);

}
