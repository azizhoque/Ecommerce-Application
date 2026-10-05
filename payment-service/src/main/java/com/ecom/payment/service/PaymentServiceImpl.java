package com.ecom.payment.service;

import org.springframework.stereotype.Service;

import com.ecom.payment.customer.PaymentRequest;
import com.ecom.payment.mapper.PaymentMapper;
import com.ecom.payment.model.PaymentStatus;
import com.ecom.payment.producer.PayentNotificationProducer;
import com.ecom.payment.producer.PaymentFailedNotificationRequest;
import com.ecom.payment.producer.PaymentSuccessNotificationRequest;
import com.ecom.payment.repository.IPaymentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

	private final IPaymentRepository repository;
	
	private final PaymentMapper mapper;
	
	private final PayentNotificationProducer notificationProducer;
	
	@Override
	public Integer createPayment(PaymentRequest request) {
		
		try {
			
			//1.save payment
			var payment = repository.save(mapper.toPayment(request));
	        

	        // Payment successful
	        notificationProducer.sendPaymentSuccessNotification(
	            new PaymentSuccessNotificationRequest(
	            		request.orderReference(), 
	            		request.amount(), 
	            		request.payMethod(), 
	            		request.customer().firstName(), 
	            		request.customer().lastName(), 
	            		request.customer().email(),
	            		PaymentStatus.PAYMENT_SUCCESS
	            		)
	        );

	        return payment.getId();

	    }catch (Exception e) {

	    	// Payment failed notification
	        notificationProducer.sendPaymentFailedNotification(
	            new PaymentFailedNotificationRequest(
	                request.orderReference(),
	                request.amount(),
	                request.payMethod(),
	                request.customer().firstName(),
	                request.customer().lastName(),
	                request.customer().email(),
	                PaymentStatus.PAYMENT_FAILED,
	                e.getMessage()
	            )
	        );

	        throw e;
	    }
	}
}
