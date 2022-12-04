package com.spring.test.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.test.entity.User;
import com.spring.test.exception.UserNotFoundException;
import com.spring.test.service.UserDaoService;

import jakarta.validation.Valid;

@RestController
public class UserResource {

	private UserDaoService service;
	
	public UserResource(UserDaoService serv) {
		this.service = serv;
	}

	//GET /users
	@GetMapping("/users")
	public List<User> retrieveAllUser(){
		return service.findAll();
	}
	
	//GET /user/{id}
	@GetMapping("/user/{id}")
	public User retrieveUser(@PathVariable int id) {
		User usr = service.findOne(id);
		if (usr == null) {
			throw new UserNotFoundException("Not found user, id:" + id);
		}
		return usr;
	}
	
	//POST /users	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		service.saveUser(user);
		URI location = ServletUriComponentsBuilder.
				fromCurrentRequest().
				path("/{id}").
				buildAndExpand(user.getId()).
				toUri();
		return ResponseEntity.created(location).build();
	}
	
	//GET /user/{id}
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteById(id);
//		if (usr == null) {
//			throw new UserNotFoundException("Not found user, id:" + id);
//		}
	}
	
}
