package com.ecom.order.mapper;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.ecom.order.model.Order;
import com.ecom.order.model.OrderStatus;
import com.ecom.order.request.OrderRequest;
import com.ecom.order.response.OrderResponse;

@Service
public class OrderMapper {

	public Order toOrder(OrderRequest request,String reference,BigDecimal totalAmount) {

		 return Order.builder()
		            .reference(reference)
		            .totalAmount(totalAmount)
		            .payment(request.payment())
		            .customerId(request.customerId())
		            .orderStatus(OrderStatus.PENDING)
		            .build();
	}
	
	public OrderResponse fromOrder(Order order) {
		return new OrderResponse(
				order.getId(),
				order.getReference(),
				order.getTotalAmount(),
				order.getPayment(),
				order.getCustomerId(),
				order.getOrderStatus(),
				order.getPaymentStatus()
				);
	}
}
