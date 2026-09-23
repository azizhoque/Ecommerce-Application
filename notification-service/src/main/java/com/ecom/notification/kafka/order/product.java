package com.ecom.notification.kafka.order;

import java.math.BigDecimal;

public record product(
		
		Integer productId,
		
		String name,
		
		String desceription,
		
		BigDecimal amount,
		
		double quantity
		) {

}
