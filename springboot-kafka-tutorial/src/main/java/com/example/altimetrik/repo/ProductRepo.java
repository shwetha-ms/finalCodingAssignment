package com.example.altimetrik.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.altimetrik.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{
}
