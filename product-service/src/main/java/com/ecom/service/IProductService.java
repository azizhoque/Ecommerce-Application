package com.ecom.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IProductService {

	public Integer addProduct(@RequestBody ProductRequest request);
}
