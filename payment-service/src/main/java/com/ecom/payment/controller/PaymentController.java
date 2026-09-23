package com.ecom.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payment.customer.PaymentRequest;
import com.ecom.payment.response.PaymentResponse;
import com.ecom.payment.service.IPaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

	private IPaymentService service;

	@PostMapping
	public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest request) {
		return new ResponseEntity<PaymentResponse>(service.createPayment(request), HttpStatus.CREATED);

	}
}
