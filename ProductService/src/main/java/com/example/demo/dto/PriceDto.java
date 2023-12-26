package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;

public class PriceDto {
	
	@NotEmpty(message="currency should not be null or empty")
	private String currency;
	
	@NotEmpty(message="amount should not be null or empty")
	private Double amount;
}
