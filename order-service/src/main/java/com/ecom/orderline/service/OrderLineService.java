package com.ecom.orderline.service;

import org.springframework.stereotype.Service;

import com.ecom.orderline.mapper.OrderLineMapper;
import com.ecom.orderline.model.OrderLine;
import com.ecom.orderline.repository.IOrderLineRepository;
import com.ecom.orderline.request.OrderLineRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderLineService {
	
	private final IOrderLineRepository repository;
	
	private final OrderLineMapper mapper;
	
	public Integer saveOrderLine(OrderLineRequest request) {
		var order = mapper.toOrderLine(request);
		return repository.save(order).getId();
	}

}
