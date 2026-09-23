package com.ecom.order.service;

import java.util.List;

import com.ecom.order.request.OrderRequest;
import com.ecom.order.response.OrderResponse;

public interface IOrderService {

	public Integer createOrder(OrderRequest request);

	public List<OrderResponse> findAll();

	public OrderResponse findById(Integer orderId);

}
