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

		// purchase the product ---by product-ms(Using RestTemplate)

		var purchaseProducts = this.productClient.purchaseProducts(request.product());
		
		  // 3. Calculate total amount from Product Service response
        BigDecimal totalAmount = purchaseProducts.stream()
                .map(product ->
                        product.price()
                                .multiply(
                                        BigDecimal.valueOf(product.quantity())
                                )
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);
		
		//create order reference for each order
		String reference = "ORD-" + UUID.randomUUID();
		

        // 5. Create order with PENDING status
        var order = mapper.toOrder(
                request,
                reference, 
                totalAmount
        );

        order.setOrderStatus(OrderStatus.PENDING);
        order.setPaymentStatus(PaymentStatus.PENDING);
        order = repository.save(order);


		// persist order

		for (PurchaseRequest purchaseRequest : request.product()) {
			orderLineService.saveOrderLine(

					new OrderLineRequest(null, 
							order.getId(), 
							purchaseRequest.productId(), 
							purchaseRequest.quantity()));
		}

		// 7. Send payment request
        paymentClient.requestOrderPayment(
                new PaymentRequest(
                        totalAmount,
                        request.payment(),
                        order.getId(),
                        order.getReference(),
                        customer
                )
        );

		return mapper.fromOrder(order);
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
