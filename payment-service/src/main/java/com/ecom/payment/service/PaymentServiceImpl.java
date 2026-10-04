package com.ecom.payment.service;

import org.springframework.stereotype.Service;

import com.ecom.payment.customer.PaymentRequest;
import com.ecom.payment.mapper.PaymentMapper;
import com.ecom.payment.notification.request.PaymentNotificationRequest;
import com.ecom.payment.producer.NotificationProducer;
import com.ecom.payment.producer.PaymentProducer;
import com.ecom.payment.repository.IPaymentRepository;
import com.ecom.payment.response.PaymentSuccessResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

	private final IPaymentRepository repository;
	
	private final PaymentMapper mapper;
	
	private final NotificationProducer notificationProducer;
	
	private final PaymentProducer paymentProducer;
	
	@Override
	public Integer createPayment(PaymentRequest request) {
		
		try {
			var payment = repository.save(mapper.toPayment(request));
	        // Payment success notification
	        notificationProducer.sendNotification(
	            new PaymentNotificationRequest(
	                request.orderReference(),
	                request.amount(),
	                request.payMethod(),
	                request.customer().firstName(),
	                request.customer().lastName(),
	                request.customer().email(),
	                "SUCCESS",
	                null
	            )
	        );

	        paymentProducer.sendPaymentSuccess(
	                new PaymentSuccessResponse(
	                    payment.getId(),
	                    request.orderId(),
	                    request.orderReference(),
	                    request.amount()
	                )
	            );
	        return payment.getId();

	    }catch (Exception e) {

	        // Payment failed notification
	        notificationProducer.sendNotification(
	            new PaymentNotificationRequest(
	                request.orderReference(),
	                request.amount(),
	                request.payMethod(),
	                request.customer().firstName(),
	                request.customer().lastName(),
	                request.customer().email(),
	                "FAILED",
	                e.getMessage()
	            )
	        );

	        throw e;
	    }
	}
}
