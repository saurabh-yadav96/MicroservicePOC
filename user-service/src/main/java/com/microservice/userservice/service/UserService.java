package com.microservice.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.userservice.entity.User;
import com.microservice.userservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User registerNewUser(User user) {
		return userRepository.save(user);
	}

	public User getUserById(Integer userId) {
		return userRepository.getById(userId);
	}

	public String updateAccountBalance(Integer userId, Double amount) {
		User user = userRepository.getById(userId);
		if (user != null) {
			user.setAvailableAmount(amount);
			userRepository.save(user);
			return "Amount Updated!!!";
		} else {
			return "User not Found";
		}

	}
}
