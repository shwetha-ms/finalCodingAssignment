package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	List<Product> findByCategory_Name(String categoryName);
	List<Product> findByCategory_Id(int id);
}
