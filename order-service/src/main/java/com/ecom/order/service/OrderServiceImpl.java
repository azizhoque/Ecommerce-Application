package com.ecom.order.service;

import org.springframework.stereotype.Service;

import com.ecom.order.client.CustomerClient;
import com.ecom.order.request.OrderRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

	private final CustomerClient client;
	
	@Override
	public Integer createOrder(OrderRequest request) {
		
		//check customer by feign-client
		
		
		return null;
	}

}
