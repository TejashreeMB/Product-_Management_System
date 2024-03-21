package com.example.pms.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.pms.entity.User;
import com.example.pms.repository.UserRepository;
import com.example.pms.service.UserService;
import com.example.pms.utility.ResponseStructure;
@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepo;
	private ResponseStructure<User> structure;
	
	
	public UserServiceImpl(UserRepository userRepo, ResponseStructure<User> structure) {
		super();
		this.userRepo = userRepo;
		this.structure = structure;
	}


	@Override
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		User uniqueUser = userRepo.save(user);
		return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value())
				.setMessage("User Saved SuccessFully..")
				.setData(uniqueUser));
	}

}
