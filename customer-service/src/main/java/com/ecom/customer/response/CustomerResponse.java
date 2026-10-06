package com.ecom.customer.response;

import com.ecom.model.Address;
import com.ecom.model.CustomerStatus;

public record CustomerResponse(
		String id,
		String firstName, 
		String lastName, 
		String email,
		CustomerStatus customerStatus,
		Address address
		) {
}
