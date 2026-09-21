package com.ecom.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.customer.request.CustomerRequest;
import com.ecom.customer.response.CustomerResponse;
import com.ecom.service.ICustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

	private final ICustomerService service;
	
	@PostMapping("/registerCustomer")
	public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request){
		String body = service.createCustomer(request);
		return new ResponseEntity<String>(body,HttpStatus.OK);
	}
	
	@PutMapping("/updateCustomer/{id}")
	public ResponseEntity<String> updateCustomer(@PathVariable String id, @RequestBody @Valid CustomerRequest request){
		String status = service.updateCustomer(id, request);
		return new ResponseEntity<String>(status,HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	public ResponseEntity<List<CustomerResponse>> findAll(){
		var list = service.findAllCustomer();
		return new ResponseEntity<List<CustomerResponse>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/exist/{customerId}")
	public ResponseEntity<Boolean> existCustomerById(@PathVariable String customerId){
		var boolean1 = service.existById(customerId);
		return new ResponseEntity<Boolean>(boolean1,HttpStatus.FOUND);
	}
	
	@GetMapping("/find/{customerId}")
	public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable String customerId){
		CustomerResponse response = service.findById(customerId);
		return new ResponseEntity<CustomerResponse>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCustomer/{customerId}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable String customerId){
		var status = service.deleteById(customerId);
		return new ResponseEntity<String>(status,HttpStatus.OK);
	}
}
