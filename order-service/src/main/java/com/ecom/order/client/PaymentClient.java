package com.ecom.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecom.order.payment.request.PaymentRequest;

@FeignClient(
		name = "payment-service",
		url = "${application.config.payment-url}")
public interface PaymentClient {

	@PostMapping
	public Integer requestOrderPayment(@RequestBody PaymentRequest request);
}
