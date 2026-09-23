package com.ecom.order.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.order.request.OrderRequest;
import com.ecom.order.response.OrderResponse;
import com.ecom.order.service.IOrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/api/order")
@RequiredArgsConstructor
public class OrderController {

	private final IOrderService service;
	
	@PostMapping("/createOrder")
	public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest request){
		service.createOrder(request);
		return new ResponseEntity<Integer>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<OrderResponse>> findAll(){
		return new ResponseEntity<List<OrderResponse>>(service.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponse> findByOrderId(@PathVariable Integer orderId){
		return new ResponseEntity<OrderResponse>(service.findById(orderId),HttpStatus.OK);
	}
}
