package com.ecom.notification.kafka.order;

import java.math.BigDecimal;

public record Product(
		
		Integer productId,
		
		String name,
		
		String desceription,
		
		BigDecimal amount,
		
		double quantity
		) {

}
