package com.ecom.order.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {

	private Integer id;
	
	private String reference;
	
	private BigDecimal totalAmount;
	
	private PaymentMethod payment;
	
	private String customerId;
	
	private List<OrderLine> orderLine;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime lastModifiedDate;
}
