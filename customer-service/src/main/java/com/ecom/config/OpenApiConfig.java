package com.ecom.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
		info = @Info(
				title = "Customer Service API", 
				version = "1.0", 
				description = "Customer Service REST API"))
public class OpenApiConfig {
}
