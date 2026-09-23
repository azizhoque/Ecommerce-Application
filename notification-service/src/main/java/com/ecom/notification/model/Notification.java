package com.ecom.notification.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import com.ecom.notification.kafka.order.OrderConfirmation;
import com.ecom.notification.kafka.payment.PaymentConfirmation;

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
@Validated
@Document
public class Notification {

	@Id
	private String id;
	
	private NotificationType type;
	
	private LocalDateTime notificationDate;
	
	private OrderConfirmation orderConfirmation;
	
	private PaymentConfirmation paymentConfirmation;
}
