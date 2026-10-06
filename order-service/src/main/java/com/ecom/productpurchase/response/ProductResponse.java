package com.ecom.productpurchase.response;

import java.math.BigDecimal;

public record ProductResponse(

		Integer id,

		String name,

		String description,

		double availableQuantity,

		BigDecimal price,

		Integer categoryId,

		String categoryName,

		String categoryDesceiption

) {

}
