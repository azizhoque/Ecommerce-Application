package com.ecom.order.service;

import com.ecom.order.request.OrderRequest;

public interface IOrderService {

	public Integer createOrder(OrderRequest request);

}
