package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
		super(String.format("%s is not found with %s %s", resourceName, fieldName, fieldValue));
	}	
	
}
