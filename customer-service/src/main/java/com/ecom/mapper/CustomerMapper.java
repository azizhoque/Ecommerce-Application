package com.ecom.mapper;

import com.ecom.customer.request.CustomerRequest;
import com.ecom.customer.response.CustomerResponse;
import com.ecom.model.Customer;

public class CustomerMapper {

	public Customer toCustomer(CustomerRequest request) {
		
		if (request==null) {
			return null;
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
