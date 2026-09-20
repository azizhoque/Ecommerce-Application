package com.ecom.model;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Validated
@Builder
@Setter
@Getter
public class Address {

	private String street;
	private String houseNumber;
	private String zipcode;
}
