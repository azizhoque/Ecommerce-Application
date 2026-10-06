package com.ecom.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecom.customer.request.CustomerRequest;
import com.ecom.customer.response.CustomerResponse;
import com.ecom.exception.CustomerNotFoundException;
import com.ecom.mapper.CustomerMapper;
import com.ecom.model.Customer;
import com.ecom.model.CustomerStatus;
import com.ecom.repository.CustomerRepository;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService{

	private final CustomerRepository repository;

	private final CustomerMapper mapper;

	public String createCustomer(@Valid CustomerRequest request) {
		var customer = repository.save(mapper.toCustomer(request));
		
		return "Customer added successfully with id: " + customer.getId();
	}

	public String updateCustomer(String id, @Valid CustomerRequest request) {
		var customer = repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(
				"Cannot update customer:: Customer not found for provided id:" + id));
		mergerCustomer(customer, request);
		repository.save(customer);
		return "Customer updated for id: " + id;
	}

	private void mergerCustomer(Customer customer, @Valid CustomerRequest request) {

		if (StringUtils.isNotBlank(request.firstName())) {
			customer.setFirstName(request.firstName());
		}
		if (StringUtils.isNotBlank(request.lastName())) {
			customer.setLastName(request.lastName());
		}
		if (StringUtils.isNotBlank(request.email())) {
			customer.setEmail(request.email());
		}
		if (request.address() != null) {
			customer.setAddress(request.address());
		}
	}

	public List<CustomerResponse> findAllCustomer() {
		return repository.findAll().stream().map(mapper::fromCustomer).collect(Collectors.toList());
	}

	public Boolean existById(String customerId) {
		return repository.findById(customerId).isPresent();
	}

	public CustomerResponse findById(String customerId) {
		return repository.findById(customerId).map(mapper::fromCustomer)
				.orElseThrow(() -> new CustomerNotFoundException("customer not found with id: " + customerId));
	}

	public String deavtiveCustomerById(String customerId) {
		Customer customer = repository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));
		customer.setCustomerStatus(CustomerStatus.DEACTIVE);
		repository.save(customer);
		return "Customer status change from ACTIVE to DEACTIVE successfully with id: " + customerId;
	}
}
