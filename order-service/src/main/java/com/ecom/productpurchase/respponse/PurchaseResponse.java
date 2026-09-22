package com.ecom.productpurchase.respponse;

import java.math.BigDecimal;

public record PurchaseResponse(
		
		Integer id,
		
		String name,
		
		String description,
		
		BigDecimal price,
		
		double quantity
		
		) {

}
