package com.ecom.payment.notification;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ecom.payment.notification.request.PaymentNotificationrequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {

	private KafkaTemplate<String, PaymentNotificationrequest> kafkaTemplate;
}
