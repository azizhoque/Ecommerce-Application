package com.ecom.notification.kafka.order;

public record Customer(
		
		String customerId,
		
		String firstName,
		
		String lastName,
		
		String email
		
		) {

}
