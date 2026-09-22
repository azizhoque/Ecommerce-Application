package com.ecom.order.request;

import java.math.BigDecimal;
import java.util.List;

import com.ecom.order.model.PaymentMethod;
import com.ecom.productpurchase.request.PurchaseRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderRequest(
		Integer id,
		String reference,
		@Positive(message = "Order amount should be positive")
		BigDecimal amount,
		
		@NotNull(message = "Payment method should be precised")
		PaymentMethod payment,
		
		@NotNull(message = "Customer should be present")
		@NotBlank(message = "Customer should be present")
		@NotEmpty(message = "Customer should be present")
		String customerId,
		
		@NotEmpty( message = "You should be purchase at least one product")
		List<PurchaseRequest> product
		
		) {

}
