package com.microservice.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.microservice.userservice.entity.User;
import com.microservice.userservice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping
	public User registerNewUser(@RequestBody User user) {
		return userService.registerNewUser(user);
	}

	@GetMapping("/{userId}")
	public User getUser(@PathVariable Integer userId) {
		return userService.getUserById(userId);
	}

	@PutMapping("/{userId}/{amount}")
	public String updateUserBalance(@PathVariable Integer userId, @PathVariable Double amount) {
		return userService.updateAccountBalance(userId, amount);
	}
}
