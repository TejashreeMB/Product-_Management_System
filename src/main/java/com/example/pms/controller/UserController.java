package com.example.pms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pms.entity.User;
import com.example.pms.service.UserService;
import com.example.pms.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	private UserService userService;
	
	
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}



	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody @Valid User user)
	{
		return userService.saveUser(user);
	}

}
