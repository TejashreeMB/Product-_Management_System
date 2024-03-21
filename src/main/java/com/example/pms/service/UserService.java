package com.example.pms.service;

import org.springframework.http.ResponseEntity;

import com.example.pms.entity.User;
import com.example.pms.utility.ResponseStructure;

public interface UserService {

	ResponseEntity<ResponseStructure<User>> saveUser(User user);

}
