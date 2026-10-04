package com.ecom.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ecom.kafka.producer.OrderPaymentSuccess;
import com.ecom.kafka.producer.OrderProducer;
import com.ecom.order.exception.BusinessException;
import com.ecom.order.model.OrderStatus;
import com.ecom.order.repository.IOrderRepository;
import com.ecom.order.response.PaymentStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentSuccessConsumer {

	private final IOrderRepository repository;
	private final OrderProducer orderProducer;
	
	@KafkaListener(
            topics = "payment-success",
            groupId = "order-payment-group"
    )
    public void handlePaymentSuccess(OrderPaymentSuccess event) {

        var order = repository.findById(event.orderId())
                .orElseThrow(() ->
                        new BusinessException(
                                "Order not found: " + event.orderId()));

        
        order.setPaymentStatus(PaymentStatus.SUCCESSS);
        order.setOrderStatus(OrderStatus.CONFIRMED);

        repository.save(order);

        // Notification Service-এ পাঠাবে
        orderProducer.sendOrderConfirmation(
                new OrderPaymentSuccess(
                		event.paymentId(),
                		event.orderId(),
                		event.orderReference(),
                		event.amount()
                		
                )
        );
   }
}
