package com.ecom.customer.request;

import com.ecom.model.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(

		@NotNull(message = "Customer First Name is required") 
		String firstName,
		@NotNull(message = "Customer Last Name is required") 
		String lastName,
		@NotNull(message = "Customer email is required") 
		@Email(message = "Customer email is not a valid email address") 
		String email,

		Address address) {
}
