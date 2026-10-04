package com.ecom.notification.kafka;

import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ecom.notification.email.EmailService;
import com.ecom.notification.kafka.order.OrderConfirmation;
import com.ecom.notification.kafka.order.OrderFailed;
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

	@KafkaListener(topics = "payment-topic", 
			       groupId = "paymentGroup")
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

	@KafkaListener(topics = "order-topic", groupId = "orderConfirmGroup")
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
		emailService.sendOrerConfirmationEmail(
				orderConfirmation.customer().email(), 
				customerName,
				orderConfirmation.amount(), 
				orderConfirmation.orderReference(), 
				orderConfirmation.product());
	}

	@KafkaListener(topics = "order-failed", groupId = "orderFailedGroup")
	public void consumeOrderFailedNotification(OrderFailed orderFailedNotification) {

		log.info("Consuming the message from order-failed Topic :: {}", orderFailedNotification);

		// যদি Notification entity-তে failed notification রাখো
		// তাহলে এখানে save করবে

		var customerName = orderFailedNotification.customer().firstName() + " "
				+ orderFailedNotification.customer().lastName();

		emailService.sendOrderFailedEmail(orderFailedNotification.customer().email(), customerName,
				orderFailedNotification.amount(), orderFailedNotification.orderReference(),
				orderFailedNotification.reason());
	}
}
