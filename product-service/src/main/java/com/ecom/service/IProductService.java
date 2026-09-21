package com.ecom.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.ecom.productrequest.ProductRequest;

public interface IProductService {

	public Integer addProduct(@RequestBody ProductRequest request);
}
