package com.ecom.customer.response;

import com.ecom.model.Address;

public record CustomerResponse(
		String id,
		String firstName, 
		String lastName, 
		String email,
		Address address) {
}
