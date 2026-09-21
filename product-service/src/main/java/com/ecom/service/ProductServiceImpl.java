package com.ecom.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecom.product.exception.ProductNotFoundException;
import com.ecom.product.exception.ProductPurchaseException;
import com.ecom.product.mapper.ProductMapper;
import com.ecom.product.request.ProductPurchaseRequest;
import com.ecom.product.response.ProductPurchaseResponse;
import com.ecom.product.response.ProductResponse;
import com.ecom.product.request.ProductRequest;
import com.ecom.repository.IProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

	private final IProductRepository repository;

	private final ProductMapper mapper;

	@Override
	public Integer addProduct(ProductRequest request) {
		var product = mapper.toProduct(request);
		repository.save(product);
		return product.getId();
	}

	@Override
	public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> request) {
		
		var productIds= request.stream().
				map(ProductPurchaseRequest::productId).
				toList();
		var storedProducts = repository.findAllByIdInOrderById(productIds);
		
		if(productIds.size() != storedProducts.size()) {
			throw new ProductPurchaseException("One or more product does not exists"); 
		}
		
		var stroedRequest = request.stream().
				sorted(Comparator.
						comparing(ProductPurchaseRequest::productId)).
				toList();
		
		var purchasedProduct = new ArrayList<ProductPurchaseResponse>();
		
		for(int i=0;i<storedProducts.size();i++) {
			
			var product = storedProducts.get(i);
			var purchaseRequest = stroedRequest.get(i);
			
			if(product.getAvailableQuantity()< purchaseRequest.quantity()) {
				throw new ProductPurchaseException("Insufficient stock quantity for product Id: "+purchaseRequest.productId());
			}
			
			var newAvailableQuantity= product.getAvailableQuantity() - purchaseRequest.quantity();
			product.setAvailableQuantity(newAvailableQuantity);
			repository.save(product);
			
			purchasedProduct.add(mapper.
					toProductPurchaseResponse(product,purchaseRequest.quantity()));
		}
		return purchasedProduct;
	}

	@Override
	public ProductResponse findById(Integer productId) {
		return repository.findById(productId).
						  map(mapper::toProductResponse).
						  orElseThrow(()-> 
						  		new ProductNotFoundException("Product not availabile for id: "+productId));
	}
	
	@Override
	public List<ProductResponse> findAll() {
		return repository.findAll().
				stream().
				map(mapper::toProductResponse).
				collect(Collectors.toList());
	}
}
