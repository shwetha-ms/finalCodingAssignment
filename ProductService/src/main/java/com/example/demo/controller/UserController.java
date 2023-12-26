//package com.example.demo.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.entity.User;
//import com.example.demo.repository.UserRepository;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//	
//	@Autowired
//	private UserRepository userRepo;
//	
//	@PostMapping
//	public ResponseEntity<User> createUser(@RequestBody User user) throws Exception{
//		User savedUser = userRepo.save(user);
//		return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
//	}
//	
//	@GetMapping
//	public ResponseEntity<List<User>> fetchUsers(){
//		List<User> user = userRepo.findAll();
//		return new ResponseEntity<>(user, HttpStatus.OK);
//	}
//	
//	
//	
//
//}
