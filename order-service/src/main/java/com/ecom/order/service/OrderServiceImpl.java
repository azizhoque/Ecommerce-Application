package com.ecom.order.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecom.kafka.producer.OrderConfirmation;
import com.ecom.kafka.producer.OrderProducer;
import com.ecom.order.client.CustomerClient;
import com.ecom.order.client.PaymentClient;
import com.ecom.order.client.ProductClient;
import com.ecom.order.exception.BusinessException;
import com.ecom.order.mapper.OrderMapper;
import com.ecom.order.payment.request.PaymentRequest;
import com.ecom.order.repository.IOrderRepository;
import com.ecom.order.request.OrderRequest;
import com.ecom.order.response.OrderResponse;
import com.ecom.orderline.request.OrderLineRequest;
import com.ecom.orderline.service.OrderLineService;
import com.ecom.productpurchase.request.PurchaseRequest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

	private final IOrderRepository repository;

	private final CustomerClient customerClient;

	private final ProductClient productClient;

	private final OrderMapper mapper;

	private final OrderLineService orderLineService;

	private final OrderProducer orderProducer;
	
	private final PaymentClient paymentClient;

	@Override
	public Integer createOrder(OrderRequest request) {

		// check customer form customer-ms by using feign-client

		var customer = this.customerClient.findCustomerByID(request.customerId())
				.orElseThrow(() -> new BusinessException(
						"Cannot create order ::No customer exists with id: " + request.customerId()));

		// purchase the product ---by product-ms(Using RestTemplate)

		var purchaseProducts = this.productClient.purchaseProducts(request.product());
		var order = this.repository.save(mapper.toOrder(request));

		// persist order

		for (PurchaseRequest purchaseRequest : request.product()) {
			orderLineService.saveOrderLine(

					new OrderLineRequest(null, 
							order.getId(), 
							purchaseRequest.productId(), 
							purchaseRequest.quantity()));
		}

		// payment confirmation

		paymentClient.requestOrderPayment(
				new PaymentRequest(
						request.amount(), 
						request.payment(), 
						order.getId(), 
						order.getReference(), 
						customer)
				);
		
		// send order confirmation ->notification-ms
		orderProducer.orderSendConfirmation(

				new OrderConfirmation(
						request.reference(), 
						request.amount(), 
						request.payment(), 
						customer,
						purchaseProducts));

		return order.getId();
	}

	@Override
	public List<OrderResponse> findAll() {
		
		return repository.findAll().
				stream().
				map(mapper::fromOrder).
				collect(Collectors.toList());
	}

	@Override
	public OrderResponse findById(Integer orderId) {
		return repository.findById(orderId).
				map(mapper::fromOrder).
				orElseThrow(()-> new EntityNotFoundException("No order found with provided id: "+orderId));
	}

}
