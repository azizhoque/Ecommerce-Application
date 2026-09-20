package com.ecom.mapper;

import com.ecom.customer.request.CustomerRequest;
import com.ecom.model.Customer;

public class CustomerMapper {

	public Customer toCustomer(CustomerRequest request) {
		
		if (request==null) {
			return null;
		}
		
		return Customer.builder().
				firstName(request.getFirstName()).
				lastName(request.getLastName()).
				email(request.getEmail()).
				address(request.getAddress()).
				build();
		
	}
}
