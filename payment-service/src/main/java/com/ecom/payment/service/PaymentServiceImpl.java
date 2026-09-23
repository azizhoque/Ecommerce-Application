package com.ecom.payment.service;

import org.springframework.stereotype.Service;

import com.ecom.payment.customer.PaymentRequest;
import com.ecom.payment.mapper.PaymentMapper;
import com.ecom.payment.notification.NotificationProducer;
import com.ecom.payment.notification.request.PaymentNotificationrequest;
import com.ecom.payment.repository.IPaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

	private IPaymentRepository repository;
	
	private PaymentMapper mapper;
	
	private NotificationProducer notificationProducer;
	
	@Override
	public Integer createPayment(PaymentRequest request) {
		var payment = repository.save(mapper.toPayment(request));
		
		notificationProducer.sendNotification(
				new PaymentNotificationrequest(
						request.orderReference(), 
						request.amount(), 
						request.payMethod(), 
						request.customer().firstName(), 
						request.customer().lastName(), 
						request.customer().email())
				);
		
		return payment.getId();
	}

}
