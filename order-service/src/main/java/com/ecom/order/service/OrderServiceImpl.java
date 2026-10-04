package com.ecom.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecom.order.client.CustomerClient;
import com.ecom.order.client.PaymentClient;
import com.ecom.order.client.ProductClient;
import com.ecom.order.exception.BusinessException;
import com.ecom.order.mapper.OrderMapper;
import com.ecom.order.model.OrderStatus;
import com.ecom.order.payment.request.PaymentRequest;
import com.ecom.order.repository.IOrderRepository;
import com.ecom.order.request.OrderRequest;
import com.ecom.order.response.OrderResponse;
import com.ecom.order.response.PaymentStatus;
import com.ecom.orderline.request.OrderLineRequest;
import com.ecom.orderline.service.OrderLineService;
import com.ecom.productpurchase.request.PurchaseRequest;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

	private final IOrderRepository repository;

	private final CustomerClient customerClient;

	private final ProductClient productClient;

	private final OrderMapper mapper;

	private final OrderLineService orderLineService;

	private final PaymentClient paymentClient;

	@Transactional
	@Override
	public OrderResponse createOrder(OrderRequest request) {

		// check customer form customer-ms by using feign-client

		var customer = this.customerClient.findCustomerByID(request.customerId())
				.orElseThrow(() -> new BusinessException(
						"Cannot create order ::No customer exists with id: " + request.customerId()));
		
	   //check product + stock and calculate total amount
		BigDecimal totalAmount = BigDecimal.ZERO;
		for(PurchaseRequest purchaseRequest:request.product()) {
			var product= productClient.findByProductId(purchaseRequest.productId());
			
			 // Check stock
            if (product.quantity() < purchaseRequest.quantity()) {

                throw new BusinessException(
                        "Insufficient stock for product id: "
                                + purchaseRequest.productId()
                );
		     }
            
            BigDecimal productTotal = product.price().multiply(BigDecimal.valueOf(purchaseRequest.quantity()));
		
            totalAmount= totalAmount.add(productTotal);
		} 
		//generate order reference
		String reference = "ORD-" + UUID.randomUUID();
		
		// Send payment request
        var paymentResponse = paymentClient.requestOrderPayment(
                new PaymentRequest(
                        totalAmount,
                        request.payment(),
                        request.id(),
                        reference,
                        customer
                )
        );
        

        // Payment failed -> DO NOT create order
        if (paymentResponse == null) {

            throw new BusinessException(
                    "Payment failed. Order was not placed."
            );
        }

        // Payment successful -> reduce product stock
        productClient.purchaseProducts(request.product());

        // Create order 
        var order = mapper.toOrder(
                request,
                reference, 
                totalAmount
        );

        order.setOrderStatus(OrderStatus.ORDER_CONFIRMED);
        order.setPaymentStatus(PaymentStatus.SUCCESSS);
        order = repository.save(order);


		// persist order

		for (PurchaseRequest purchaseRequest : request.product()) {
			orderLineService.saveOrderLine(

					new OrderLineRequest(null, 
							order.getId(), 
							purchaseRequest.productId(), 
							purchaseRequest.quantity()));
		}

		

		return mapper.fromOrder(order);
	}

	@Override
	public List<OrderResponse> findAll() {

		return repository.findAll().stream().map(mapper::fromOrder).collect(Collectors.toList());
	}

	@Override
	public OrderResponse findById(Integer orderId) {
		return repository.findById(orderId).map(mapper::fromOrder)
				.orElseThrow(() -> new EntityNotFoundException("No order found with provided id: " + orderId));
	}

}
