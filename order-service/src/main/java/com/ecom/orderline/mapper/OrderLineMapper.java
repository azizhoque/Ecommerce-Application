package com.ecom.orderline.mapper;

import org.springframework.stereotype.Service;

import com.ecom.order.model.Order;
import com.ecom.orderline.model.OrderLine;
import com.ecom.orderline.request.OrderLineRequest;
import com.ecom.orderline.response.OrderLineResponse;

@Service
public class OrderLineMapper {

	public OrderLine toOrderLine(OrderLineRequest request) {
		return OrderLine.builder().
				id(request.id()).
				order(Order.
						builder().
						id(request.orderId()).
						build()).
				productId(request.productId()).
				quantity(request.quantity()).
				build();
	}
	
	public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
		return new OrderLineResponse(
				orderLine.getId(), 
				orderLine.getQuantity());
	}

}
