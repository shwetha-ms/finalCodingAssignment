package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.Atrributes;
import com.example.demo.entity.Category;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.Price;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description="Product DTO model Information")
public class ProductDto {
	
	private Long id;
	
	@NotEmpty(message="product name should not be null or empty")
	private String name;
	
	@NotEmpty(message="brand should not be null or empty")
	private String brand;
	
	@NotEmpty(message="description should not be null or empty")
	private String description;
	
	@OneToOne
	private Price price;
	
	@OneToOne
	private Inventory inventory;
	
	@OneToMany
	private List<Atrributes> attributes;
	
	@ManyToOne
	private Category category;
	
}
