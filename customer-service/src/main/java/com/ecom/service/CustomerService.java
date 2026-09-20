package com.ecom.service;

import org.springframework.stereotype.Service;

import com.ecom.customer.request.CustomerRequest;
import com.ecom.exceptionhandle.CustomerNotFoundException;
import com.ecom.mapper.CustomerMapper;
import com.ecom.model.Customer;
import com.ecom.repository.CustomerRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository repository;

	private CustomerMapper mapper;

	public String createCustomer(@Valid CustomerRequest request) {
		var customer = repository.save(mapper.toCustomer(request));
		return customer.getId();
	}

	public void updateCustomer(String id, @Valid CustomerRequest request) {

		var customer = repository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not found with id: " + id));
		customer.setFirstName(request.getFirstName());
		customer.setLastName(request.getLastName());
		customer.setEmail(request.getEmail());
		customer.setAddress(request.getAddress());

		repository.save(customer);
	}
}
