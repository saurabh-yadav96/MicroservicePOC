package com.microservice.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservice.authservice.entity.UserCredential;
import com.microservice.authservice.repository.UserCredentialRepository;
import com.microservice.authservice.util.JwtService;

@Service
public class AuthService {

	@Autowired
	private UserCredentialRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	public String saveUser(UserCredential credential) {
		credential.setPassword(passwordEncoder.encode(credential.getPassword()));
		repository.save(credential);
		return "User added to System!!!";
	}

	public String generateToken(String userName) {
		return jwtService.generateToken(userName);
	}

	public void validateToken(String token) {
		jwtService.validateToken(token);
	}

}
