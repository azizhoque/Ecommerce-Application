package com.ecom.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ecom.kafka.producer.OrderPaymentFailed;
import com.ecom.kafka.producer.OrderProducer;
import com.ecom.order.model.OrderStatus;
import com.ecom.order.repository.IOrderRepository;
import com.ecom.order.response.PaymentStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentFailedConsumer {

	private final IOrderRepository repository;
    private final OrderProducer orderProducer;

    @KafkaListener(
            topics = "payment-failed",
            groupId = "order-payment-group"
    )
    public void handlePaymentFailed(OrderPaymentFailed event) {

        var order = repository.findById(event.orderId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order not found: " + event.orderId()));

        order.setPaymentStatus(PaymentStatus.FAILED);
        order.setOrderStatus(OrderStatus.FAILED);

        repository.save(order);
        
        //send to Notification Service
        
        orderProducer.sendOrderFailed(
                new OrderPaymentFailed(
                        order.getId(),
                        order.getReference(),
                        order.getTotalAmount(),
                        order.getPayment(),
                        event.customerFirstName(),
                        event.customerLastName(),
                        event.customerEmail(),
                        event.reason()
                        )
                );
    }
}
