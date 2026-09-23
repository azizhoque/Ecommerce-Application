package com.ecom.payment.service;

import org.springframework.stereotype.Service;

import com.ecom.payment.customer.PaymentRequest;
import com.ecom.payment.repository.IPaymentRepository;
import com.ecom.payment.response.PaymentResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

	private IPaymentRepository repository;
	
	@Override
	public PaymentResponse createPayment(PaymentRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
