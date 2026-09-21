package com.ecom.product.request;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
		
		@NotNull(message = "Product id is mantadory")
		Integer productId,
		@NotNull(message = "Quantity is mantadory")
		double quantity
		) {

}
