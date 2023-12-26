package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title ="Spring Boot Microservice - Product Service",
		description ="Spring Boot Microservice - Product Service Documentation"))
@EnableDiscoveryClient
public class SpringBootRestFulWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestFulWebServiceApplication.class, args);
	}
	
	@Bean
	public static ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
