package com.ecom.order.mapper;

import org.springframework.stereotype.Service;

import com.ecom.order.model.Order;
import com.ecom.order.request.OrderRequest;
import com.ecom.order.response.OrderResponse;

@Service
public class OrderMapper {

	public Order toOrder(OrderRequest request,String reference) {

		return Order.builder().
				id(request.id()).
				reference(reference).
				payment(request.payment()).
				customerId(request.customerId()).build();
	}
	
	public OrderResponse fromOrder(Order order) {
		return new OrderResponse(
				order.getId(),
				order.getReference(),
				order.getTotalAmount(),
				order.getPayment(),
				order.getCustomerId(),
				order.getOrderStatus()
				
				);
	}
}
