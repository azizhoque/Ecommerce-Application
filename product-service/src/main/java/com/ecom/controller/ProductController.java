package com.ecom.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.product.request.ProductPurchaseRequest;
import com.ecom.product.response.ProductPurchaseResponse;
import com.ecom.product.response.ProductResponse;
import com.ecom.product.request.ProductRequest;
import com.ecom.service.IProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

	private final IProductService service;

	@PostMapping("/addProduct")
	public ResponseEntity<Integer> createProduct(@RequestBody ProductRequest request) {
		var product = service.addProduct(request);
		return new ResponseEntity<Integer>(product,HttpStatus.CREATED);
	}

	@PostMapping("/purchase")
	public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(@RequestBody List<ProductPurchaseRequest> request) {
		var purchase = service.purchaseProduct(request);
		return new ResponseEntity<List<ProductPurchaseResponse>>(purchase,HttpStatus.OK);
	}
	
	@GetMapping("findById/{product-id}")
	public ResponseEntity<ProductResponse> findById(@PathVariable("product-id") Integer productId){
		var product = service.findById(productId);
		return new ResponseEntity<ProductResponse>(product,HttpStatus.OK);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<ProductResponse>> findAll(){
		var product = service.findAll();
		return new ResponseEntity<List<ProductResponse>>(product,HttpStatus.OK);
	}
}
