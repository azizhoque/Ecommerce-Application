package com.ecom.kafka.producer;

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
public class OrderProducer {

	private final KafkaTemplate<String, Object> kafkaTemplate;
	
	public void sendOrderConfirmation(OrderConfirmationNotification event) {

        Message<OrderConfirmationNotification> message =
                MessageBuilder
                        .withPayload(event)
                        .setHeader(KafkaHeaders.TOPIC, "order-confirmed")
                        .build();

        kafkaTemplate.send(message);
    }

}
