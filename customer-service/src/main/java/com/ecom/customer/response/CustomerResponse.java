package com.ecom.customer.response;

import com.ecom.model.Address;

public record CustomerResponse(
		String firstName, 
		String lastName, 
		String email,
		Address address) {
}
