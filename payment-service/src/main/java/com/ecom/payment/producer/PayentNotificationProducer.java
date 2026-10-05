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

	public void sendPaymentSuccessNotification(PaymentSuccessNotificationRequest successRequest) {

		log.info("Sending notification with body <{}> ", successRequest);

		Message<PaymentSuccessNotificationRequest> message = MessageBuilder.withPayload(successRequest)
				.setHeader(KafkaHeaders.TOPIC, "payment-sucess").build();

		kafkaTemplate.send(message);
	}

	public void sendPaymentFailedNotification(PaymentFailedNotificationRequest failedRequest) {

		log.info("Sending notification with body <{}> ", failedRequest);

		Message<PaymentFailedNotificationRequest> message = MessageBuilder.withPayload(failedRequest)
				.setHeader(KafkaHeaders.TOPIC, "payment-failed").build();

		kafkaTemplate.send(message);
	}
}
