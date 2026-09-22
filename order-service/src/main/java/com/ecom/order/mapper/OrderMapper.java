package com.ecom.order.mapper;

import org.springframework.stereotype.Service;

import com.ecom.order.model.Order;
import com.ecom.order.request.OrderRequest;

@Service
public class OrderMapper {

	public Order toOrder(OrderRequest request) {

		return Order.builder().
				id(request.id()).
				reference(request.reference()).
				payment(request.payment()).
				totalAmount(request.amount()).
				customerId(request.customerId()).build();
	}
}
