package com.ecom.notification.kafka;

import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ecom.notification.email.EmailService;
import com.ecom.notification.kafka.order.OrderConfirmation;
import com.ecom.notification.kafka.payment.PaymentFailedConsumer;
import com.ecom.notification.kafka.payment.PaymentSuccessConsumer;
import com.ecom.notification.model.Notification;
import com.ecom.notification.model.NotificationType;
import com.ecom.notification.repository.INotificationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

	private final INotificationRepository notificationRepository;

	private final EmailService emailService;

	@KafkaListener(topics = "payment-sucess", 
			       groupId = "paymentSucessGroup")
	public void consumePaymentSuccessNotification(PaymentSuccessConsumer paymentSuccess) {

		log.info("Consuming the message from payment-topic Tpoic :: %s", paymentSuccess);

		notificationRepository.save(

				Notification.builder().
				type(NotificationType.PAYMENT_SUCCESS).
				notificationDate(LocalDateTime.now()).
			    paymentSuccess(paymentSuccess).
			    build());

		// send mail
		var customerName = paymentSuccess.customerFirstName() + " " + paymentSuccess.customerLastName();
		emailService.sendPaymentSuccessEmail(
				paymentSuccess.customerEmail(), 
				customerName,
				paymentSuccess.amount(), 
				paymentSuccess.orderReference());
	}

	@KafkaListener(topics = "order-confirmed", groupId = "orderConfirmGroup")
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

	@KafkaListener(topics = "payment-failed", groupId = "paymentFailedGroup")
	public void consumePaymentFailedNotification(PaymentFailedConsumer paymentFailed) {

		log.info("Consuming the message from payment-topic Tpoic :: %s", paymentFailed);

		notificationRepository.save(

				Notification.builder().
				type(NotificationType.PAYMENT_FAILED).
				notificationDate(LocalDateTime.now()).
			    paymentFailed(paymentFailed).
			    build());

		// send mail
		var customerName = paymentFailed.customerFirstName() + " " + paymentFailed.customerLastName();
		emailService.sendPaymentFailedEmail(
				paymentFailed.customerEmail(), 
				customerName,
				paymentFailed.amount(), 
				paymentFailed.orderReference(),
				paymentFailed.reason());
	}
}
