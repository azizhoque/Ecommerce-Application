package com.ecom.payment.notification;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.ecom.payment.notification.request.PaymentNotificationrequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {

	private KafkaTemplate<String, PaymentNotificationrequest> kafkaTemplate;
	
	public void sendNotification(PaymentNotificationrequest request) {
		
		log.info("Sending notification with body <{}> ", request);
		
		Message<PaymentNotificationrequest> message = 
				MessageBuilder.
				withPayload(request).
				setHeader(KafkaHeaders.TOPIC, "payment-topic").
				build();
		
		kafkaTemplate.send(message);
	}
}
