package com.ecom.order.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service",
             url = "${application.config.customer-url}")
public interface CustomerClient {

	@GetMapping("/find/{customerId}")
	Optional<CustomerResponse> findCustomerByID(@PathVariable String customerId);
}
