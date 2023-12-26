package com.example.altimetrik.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.altimetrik.entity.Product;
import com.example.altimetrik.kafka.KafkaProducer;
import com.example.altimetrik.repo.ProductRepo;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/kafka")
@AllArgsConstructor
public class ProductController {

    private KafkaProducer kafkaProducer;
    
    private ProductRepo productRepo;

    @PostMapping("/product")
    public ResponseEntity<String> getProducts(@RequestBody Product product){
        kafkaProducer.sendMessage(product);
        return ResponseEntity.ok("saved Product sent to kafka topic");
    }
    
    @GetMapping("/product/{id}")
    public ResponseEntity<String> getProductById(@PathVariable int id){
    	Product product = productRepo.getById(id);
        kafkaProducer.sendMessage(product);
        return ResponseEntity.ok("get product data sent to kafka topic");
    }
    
    @GetMapping("/product")
    public ResponseEntity<String> loadProducts(){
    	List<Product> products = productRepo.findAll();
    	products.stream().forEach(product -> {
    		kafkaProducer.sendMessage(product);
    	});
        return ResponseEntity.ok("load all products data sent to kafka topic");
    }
    
    
}
