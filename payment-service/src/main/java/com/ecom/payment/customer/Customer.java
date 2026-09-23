package com.ecom.payment.customer;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Validated
public record Customer(
		
		String id,
		
		@NotNull(message = "First name is required")
		String firstName,
		
		@NotNull(message = "Last name is required")
		String lastName,
		
		@NotNull(message = "Email is required")
		@Email(message = "Customer email is not correctly formatted")
		String email
		
		) {

}
