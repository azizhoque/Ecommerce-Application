package com.ecom.orderline.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecom.orderline.mapper.OrderLineMapper;
import com.ecom.orderline.repository.IOrderLineRepository;
import com.ecom.orderline.request.OrderLineRequest;
import com.ecom.orderline.response.OrderLineResponse;

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

	public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
		return repository.findAllByOrderId(orderId).
				stream().
				map(mapper::toOrderLineResponse).
				collect(Collectors.toList());
	}

}
