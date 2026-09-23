package com.ecom.notification.kafka;

import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

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
	
	@KafkaListener(topics = "payment-topic")
	public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) {
		
		log.info("Consuming the message from payment-topic Tpoic :: %s", paymentConfirmation);
		
		notificationRepository.save(
				
				Notification.builder().
				type(NotificationType.PAYMENT_CONFIRMATION).
				notificationDate(LocalDateTime.now()).
				paymentConfirmation(paymentConfirmation).
				build()
				);
	}
}
