package com.ecom.service;

import org.springframework.stereotype.Service;

import com.ecom.customer.request.CustomerRequest;
import com.ecom.exceptionhandle.CustomerNotFoundException;
import com.ecom.mapper.CustomerMapper;
import com.ecom.model.Customer;
import com.ecom.repository.CustomerRepository;

import io.micrometer.common.util.StringUtils;
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
		var customer = repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(
				"Cannot update customer:: Customer not found for provided id:" + id));
		mergerCustomer(customer, request);
		repository.save(customer);
	}

	private void mergerCustomer(Customer customer, @Valid CustomerRequest request) {

		if (StringUtils.isNotBlank(request.getFirstName())) {
			customer.setFirstName(request.getFirstName());
		}
		if (StringUtils.isNotBlank(request.getLastName())) {
			customer.setLastName(request.getLastName());
		}
		if (StringUtils.isNotBlank(request.getEmail())) {
			customer.setEmail(request.getEmail());
		}
		if (request.getAddress() != null) {
			customer.setAddress(request.getAddress());
		}
	}
}
