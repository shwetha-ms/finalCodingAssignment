package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Category;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.repository.CategoryRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(
		name="CRUD Rest APIs for Categoty - For Admin to create category"
	)
	@RestController
	@Slf4j
	@RequestMapping("/admin/category")
	public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@PostMapping
	@Operation(summary="Create Category Rest API")
	@ApiResponse(responseCode="201", description="Http Status 201 CREATED")
	public ResponseEntity<Category> createCategory(@RequestBody Category category){
		log.info("Category Controller - Create category method called");
		Category savedCategory = categoryRepository.save(category);
		return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
	}
	
	@GetMapping
	@Operation(summary="Fetch All Category Rest API")
	@ApiResponse(responseCode="200", description="Http Status 20O OK")
	public ResponseEntity<List<Category>> getCategory(){
		log.info("Category Controller - fetch all categories method called");
		List<Category> categories = categoryRepository.findAll();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@Operation(summary="Fetch Category By Id")
	@ApiResponse(responseCode="200", description="Http Status 302")
	public ResponseEntity<Category> getCategoryById(@PathVariable int id){
		log.info("Category Controller - fetch category by Id");
		Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException());
		return new ResponseEntity<>(category, HttpStatus.FOUND);
	}
	
	@PutMapping("/{id}")
	@Operation(summary="Update Category Rest API")
	@ApiResponse(responseCode="201", description="Http Status 200 OK")
	public ResponseEntity<Category> getCategoryById(@PathVariable int id, @RequestBody Category category){
		log.info("Category Controller - update category method called");
		categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException());
		categoryRepository.save(category);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

}
