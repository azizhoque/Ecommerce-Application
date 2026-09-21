package com.ecom.service;

import java.util.List;

import com.ecom.customer.request.CustomerRequest;
import com.ecom.customer.response.CustomerResponse;

import jakarta.validation.Valid;

public interface ICustomerService {

	public String createCustomer(@Valid CustomerRequest request);

	public String updateCustomer(String id, @Valid CustomerRequest request);

	public List<CustomerResponse> findAllCustomer();

	public Boolean existById(String customerId);

	public CustomerResponse findById(String customerId);

	public String deleteById(String customerId);
}
