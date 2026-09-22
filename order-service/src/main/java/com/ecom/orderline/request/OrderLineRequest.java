package com.ecom.orderline.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderLineRequest(
		
		Integer id, 
		
		Integer orderId, 
		
		@NotNull(message = "Product id is mandatory") 
		Integer productId,
		
		@Positive(message = "Quantity mandatory") 
		double quantity
		) 
        {
}


