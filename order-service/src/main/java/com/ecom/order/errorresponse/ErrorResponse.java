package com.ecom.order.errorresponse;

import java.util.Map;

public record ErrorResponse(
		
		Map<String, String> errors
		) {

}
