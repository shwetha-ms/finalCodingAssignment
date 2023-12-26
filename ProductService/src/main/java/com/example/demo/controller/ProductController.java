package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDto;
import com.example.demo.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Tag(
	name=" CRUD Rest APIs for Product"
)
@RestController
@Slf4j
public class ProductController {
	
	
	@Autowired
	private ProductService productService;	
	
	@PostMapping("/admin/product/{categoryId}")
	@Operation(summary="Create Product Rest API")
	@ApiResponse(responseCode="201", description="Http Status 201 CREATED")
	public ResponseEntity<ProductDto> createProduct(@PathVariable int categoryId, @Valid @RequestBody ProductDto productDto){
		log.info("Product Controller - Create product method called");
		ProductDto savedProductDto = productService.saveProduct(productDto,categoryId);
		return new ResponseEntity<>(savedProductDto, HttpStatus.CREATED);
	}
	
	@Operation(summary="Fetch All Products Rest API")
	@ApiResponse(responseCode="200", description="Http Status 200 SUCSESS")
	@GetMapping("/product")
	public ResponseEntity<List<ProductDto>> getProducts(){
		log.info("Product Controller - get all products method called");
		List<ProductDto>  productDto = productService.ProductList();
		return new ResponseEntity<>(productDto, HttpStatus.OK);	
	}
	
	
	@GetMapping("/product/category")
	@Operation(summary="Fetch All Products Based On category name")
	@ApiResponse(responseCode="200", description="Http Status 200 SUCSESS")
	public ResponseEntity<List<ProductDto>> getProductsByCategoryName(@RequestParam String categoryName){
		log.info("Product Controller - get all products method called by categoryName");
		List<ProductDto>  productDto = productService.ProductCategoryByName(categoryName);
		return new ResponseEntity<>(productDto, HttpStatus.OK);	
	}
	
	@GetMapping("/product/category/{id}")
	@Operation(summary="Fetch All Products Based On category Id")
	@ApiResponse(responseCode="200", description="Http Status 200 SUCSESS")
	public ResponseEntity<List<ProductDto>> getProductsByCategoryId(@PathVariable int  id){
		log.info("Product Controller - get all products method called by category Id");
		List<ProductDto>  productDto = productService.ProductCategoryById(id);
		return new ResponseEntity<>(productDto, HttpStatus.OK);	
	}
	
	@GetMapping("/product/{id}")
	@Operation(summary="Get Product Rest API")
	@ApiResponse(responseCode="200", description="Http Status 200 SUCSESS")
	public ResponseEntity<ProductDto> getProduct(@PathVariable long id){
		log.info("Product Controller - get product method called");
		ProductDto productDto = productService.getProduct(id);
		return new ResponseEntity<>(productDto, HttpStatus.OK);
	}
	
	@PutMapping("/admin/product/{id}")
	@Operation(summary="Update Product Rest API")
	@ApiResponse(responseCode="200", description="Http Status 200 SUCSESS")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable long id, @RequestBody ProductDto productDto){
		log.info("Product Controller - update product method called");
		ProductDto updatedProductDto = productService.updateProduct(id, productDto);
		return new ResponseEntity<>(updatedProductDto, HttpStatus.OK);
	}
	
	
	@GetMapping("/product/pagination/{offset}/{pageSize}")
	public ResponseEntity<Page<ProductDto>> getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
		log.info("Product Controller - get products with pagination");
		Page<ProductDto> page = productService.findProductsWithPagination(offset, pageSize);
		return new ResponseEntity<>(page, HttpStatus.OK);
	}
	
	@GetMapping("/product/productByPriceOrAvailability")
	public ResponseEntity<Page<ProductDto>> getProductsWithPaginationAndSort(
												@RequestParam(required=false) String field1,
												@RequestParam(required=false) String field2) {
		log.info("Product Controller - get products with pagination and sorting");
		Page<ProductDto> page = productService.findProductsWithSorting(field1, field2);
		return new ResponseEntity<>(page, HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/product/{id}")
	@Operation(summary="Delete Product Rest API")
	public ResponseEntity<String> deleteProduct(@PathVariable long id){
		log.info("Product Controller - delete product mehtod called");
		productService.removeProduct(id);
		return new ResponseEntity<>("Product Removed Sucessfully", HttpStatus.OK);
	}
}
