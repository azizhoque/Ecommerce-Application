package com.ecom.orderline.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.ecom.order.model.Order;
import com.ecom.order.model.PaymentMethod;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class OrderLine {

	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	private Integer productId;
	private double quantity;
}
