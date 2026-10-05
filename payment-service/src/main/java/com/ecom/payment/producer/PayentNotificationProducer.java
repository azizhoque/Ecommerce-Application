package com.ecom.payment.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PayentNotificationProducer {

	private final KafkaTemplate<String, PaymentFailedNotificationRequest> kafkaTemplate;

	public void sendPaymentSuccessNotification(PaymentSuccessNotificationRequest request) {

		log.info("Sending notification with body <{}> ", request);

		Message<PaymentSuccessNotificationRequest> message = MessageBuilder.withPayload(request)
				.setHeader(KafkaHeaders.TOPIC, "payment-sucess").build();

		kafkaTemplate.send(message);
	}

	public void sendPaymentFailedNotification(PaymentFailedNotificationRequest request) {

		log.info("Sending notification with body <{}> ", request);

		Message<PaymentFailedNotificationRequest> message = MessageBuilder.withPayload(request)
				.setHeader(KafkaHeaders.TOPIC, "payment-failed").build();

		kafkaTemplate.send(message);
	}
}
