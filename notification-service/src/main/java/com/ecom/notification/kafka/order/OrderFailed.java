package com.ecom.notification.kafka.order;

import java.math.BigDecimal;

import com.ecom.notification.kafka.payment.PaymentMethod;

public record OrderFailed(

		String orderReference,

		BigDecimal amount,

		PaymentMethod payMethod,

		Customer customer,

		String reason

) {

}
