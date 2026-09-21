package com.ecom.service;

import java.util.List;

import com.ecom.product.request.ProductPurchaseRequest;
import com.ecom.product.response.ProductPurchaseResponse;
import com.ecom.product.response.ProductResponse;
import com.ecom.product.request.ProductRequest;

public interface IProductService {

	public Integer addProduct(ProductRequest request);

	public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> response);

	public ProductResponse findById(Integer productId);

	public List<ProductResponse> findAll();
}
