package com.ecom.product.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(

		@NotNull(message = "Product name is required")
		String name, 
		@NotNull(message = "Product description is required")
		String description, 
		@Positive(message = "Availaible questity should be positive")
		double availableQuantity, 
		@Positive(message = "Product price should be positive")
		BigDecimal price,
		@NotNull(message = "Product  is categoryrequired")
		Integer categoryId

) {

}
