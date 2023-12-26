package com.example.demo.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InventoryDto {
	private int id;
	
	@PositiveOrZero(message="total should be non negative")
	private int total;
	
	@PositiveOrZero(message="available should be non negative")
    private int available;
	
	@PositiveOrZero(message="reserved should be non negative")
    private int reserved;
}
