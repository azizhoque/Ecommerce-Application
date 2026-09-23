package com.ecom.notification.kafka;

import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ecom.notification.email.EmailService;
import com.ecom.notification.kafka.order.OrderConfirmation;
import com.ecom.notification.kafka.payment.PaymentConfirmation;
import com.ecom.notification.model.Notification;
import com.ecom.notification.model.NotificationType;
import com.ecom.notification.repository.INotificationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

	private INotificationRepository notificationRepository;

	private EmailService emailService;

	@KafkaListener(topics = "payment-topic")
	public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) {

		log.info("Consuming the message from payment-topic Tpoic :: %s", paymentConfirmation);

		notificationRepository.save(

				Notification.builder().
				type(NotificationType.PAYMENT_CONFIRMATION).
				notificationDate(LocalDateTime.now()).
				paymentConfirmation(paymentConfirmation).
				build());
		
		// send mail
		var customerName = paymentConfirmation.customerFirstName() + " " + paymentConfirmation.customerLastName();
		emailService.sendPaymentSuccessEmail(
				paymentConfirmation.customerEmail(), 
				customerName,
				paymentConfirmation.amount(), 
				paymentConfirmation.orderReference());
	}

	@KafkaListener(topics = "order-topic")
	public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) {

		log.info("Consuming the message from order-topic Tpoic :: %s", orderConfirmation);

		notificationRepository.save(

				Notification.builder().
				type(NotificationType.ORDER_CONFIRMATION).
				notificationDate(LocalDateTime.now()).
				orderConfirmation(orderConfirmation).
				build());
		// send mail
		var customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName();
		emailService.sendPaymentSuccessEmail(
				orderConfirmation.customer().email(), 
				customerName,
				orderConfirmation.amount(), 
				orderConfirmation.orderReference());
	}
}
