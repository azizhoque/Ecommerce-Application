package com.ecom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.service.IProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

	private final IProductService service;
	
	@PostMapping("/addProduct")
	public ResponseEntity<Integer> createProduct(@RequestBody ProductRequest request){
		
	}
}
