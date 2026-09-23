package com.ecom.notification.email;

import lombok.Getter;

public enum EmailTemplate {

	PAYMENT_CNFIRMATION("patment-confirmation.html","Payment successfully processed"),
	
	ORDER_CNFIRMATION("order-confirmation.html","Order confirmation"),;

	@Getter
	private final String template;
	
	@Getter
	private final String subject;
	
	
	EmailTemplate(String template, String subject) {
		this.template = template;
		this.subject = subject;
	}
	
}
