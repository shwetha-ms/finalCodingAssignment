package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Price;

public interface PriceRepository extends JpaRepository<Price, Integer>{
}
