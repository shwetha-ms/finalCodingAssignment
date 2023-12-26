package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;

public interface ProductService {
	
	public ProductDto saveProduct(ProductDto productDto, int categoryId);
	
	public ProductDto updateProduct(Long id, ProductDto productDto);
	
	public ProductDto getProduct(Long id);
	
	public List<ProductDto> ProductList();
	
	public void removeProduct(long id);
	
	public List<ProductDto> ProductCategoryByName(String categoryName);
	
	public List<ProductDto> ProductCategoryById(int id);

	public Page<ProductDto> findProductsWithPagination(int offset, int pageSize);
	
	public Page<ProductDto> findProductsWithSorting(String field1, String field2);

}
