package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AttributesRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.PriceRepository;
import com.example.demo.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private AttributesRepository attributeRepository;
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private PriceRepository priceRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	@Transactional
	public ProductDto saveProduct(ProductDto productDto, int categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryNotFoundException());
		productDto.setCategory(category);
		attributeRepository.saveAll(productDto.getAttributes());
		priceRepository.save(productDto.getPrice());
		inventoryRepository.save(productDto.getInventory());
		categoryRepository.save(category);
		Product savedProduct = productRepository.save(modelMapper.map(productDto, Product.class));
		return modelMapper.map(savedProduct, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(Long id, ProductDto productDto) {
		Product product= productRepository.findById(id).orElseThrow(() -> 
		new ProductNotFoundException("invalid product id"));
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		attributeRepository.saveAll(productDto.getAttributes());
		priceRepository.save(productDto.getPrice());
		inventoryRepository.save(productDto.getInventory());
		productRepository.save(modelMapper.map(productDto, Product.class));
		return modelMapper.map(product, ProductDto.class);
	}

	@Override
	public ProductDto getProduct(Long id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new 
				ResourceNotFoundException("product", "id", id+""));
		return modelMapper.map(product, ProductDto.class);	
	}

	@Override
	public List<ProductDto> ProductList() {
		List<Product> products = productRepository.findAll();
		return products.stream()
				.filter(product -> product.getInventory().getAvailable() > 0)
							.map((product) -> modelMapper.map(product, ProductDto.class))
							.collect(Collectors.toList());
	}

	@Override
	public void removeProduct(long id) {
		productRepository.deleteById(id);
	}


	@Override
	public List<ProductDto> ProductCategoryByName(String categoryName) {
		List<Product> products = productRepository.findByCategory_Name(categoryName);
		return products.stream()
				.filter(product -> product.getInventory().getAvailable() > 0)
							.map((product) -> modelMapper.map(product, ProductDto.class))
							.collect(Collectors.toList());
	}

	@Override
	public List<ProductDto> ProductCategoryById(int id) {
		List<Product> products = productRepository.findByCategory_Id(id);
		return products.stream()
							.map((product) -> modelMapper.map(product, ProductDto.class))
							.collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	public Page<ProductDto> findProductsWithPagination(int offset, int pageSize){
		Page<Product> products  =productRepository.findAll(PageRequest.of(offset, pageSize));
		return (Page<ProductDto>) products.stream()
				.filter(product -> product.getInventory().getAvailable() > 0)
				.map((product) -> modelMapper.map(product, ProductDto.class))
				.collect(Collectors.toList());
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<ProductDto> findProductsWithSorting(String field1, String field2) {
		List<Order> orders = new ArrayList<>();
		if(field1 != null) {
			orders.add(new Order(Direction.DESC, field1));
		}
		if(field2 != null) {
			orders.add(new Order(Direction.DESC, field2));
		}
		Page<Product> products = productRepository.findAll(PageRequest.of(0, 1,Sort.by(orders)));
		return (Page<ProductDto>) products.stream()
				.filter(product -> product.getInventory().getAvailable() > 0)
				.map((product) -> modelMapper.map(product, ProductDto.class))
				.collect(Collectors.toList());
	}
}

