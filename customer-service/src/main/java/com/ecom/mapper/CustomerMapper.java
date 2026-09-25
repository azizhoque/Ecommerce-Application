package com.ecom.mapper;

import org.springframework.stereotype.Component;

import com.ecom.customer.request.CustomerRequest;
import com.ecom.customer.response.CustomerResponse;
import com.ecom.model.Customer;

@Component
public class CustomerMapper {

	public Customer toCustomer(CustomerRequest request) {

		if (request == null) {
			throw new IllegalArgumentException("CustomerRequest cannot be null or mapper can not be null");
		}
		return Customer.builder().
				firstName(request.firstName()).
				lastName(request.lastName()).
				email(request.email()).
				address(request.address()).
				build();
	}

	public CustomerResponse fromCustomer(Customer customer) {
		return new CustomerResponse(
				customer.getId(), 
				customer.getFirstName(), 
				customer.getLastName(),
				customer.getEmail(), 
				customer.getAddress());
	}
}
