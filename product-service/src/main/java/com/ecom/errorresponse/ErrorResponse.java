package com.ecom.errorresponse;

import java.util.Map;

public record ErrorResponse(
		
		Map<String, String> errors
		) {

}
