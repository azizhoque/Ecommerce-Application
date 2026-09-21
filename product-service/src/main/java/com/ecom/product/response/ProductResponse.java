package com.ecom.product.response;

import java.math.BigDecimal;

public record ProductResponse(
		String name, 
		
		String description, 
		
		double availableQuantity, 
		
		BigDecimal price,
		
		Integer categoryId,
		
		String categoryName,
		
		String categoryDesceiption
		) {
}
