package com.ecom.payment.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.ecom.payment.response.PaymentSuccessResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentProducer {

	private final KafkaTemplate<String, PaymentSuccessResponse> kafkaTemplate;

	public void sendPaymentSuccess(PaymentSuccessResponse response) {

		Message<PaymentSuccessResponse> message =
	            MessageBuilder
	                .withPayload(response)
	                .setHeader(KafkaHeaders.TOPIC, "payment-success")
	                .build();
		kafkaTemplate.send(message);
	}
}
