package com.ecom.product.mapper;

import org.springframework.stereotype.Service;

import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.product.request.ProductRequest;

@Service
public class ProductMapper {

	public Product toProduct(ProductRequest request) {
		
		return Product.builder().
				name(request.name()).
				description(request.description()).
				availableQuantity(request.availableQuantity()).
				price(request.price()).
				category(Category.builder().id(request.categoryId()).build()).
				build();
	}

}
