package com.ecom.orderline.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.orderline.response.OrderLineResponse;
import com.ecom.orderline.service.OrderLineService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/orderLines")
@RequiredArgsConstructor
public class OrderLineController {

	private OrderLineService service;

	@GetMapping("/{orderId}")
	public ResponseEntity<List<OrderLineResponse>> findByOrderId(@PathVariable Integer orderId) {
		return new ResponseEntity<List<OrderLineResponse>>(service.findAllByOrderId(orderId), HttpStatus.OK);
	}
}
