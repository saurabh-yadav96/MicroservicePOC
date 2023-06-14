package com.microservice.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.microservice.authservice.dto.AuthRequest;
import com.microservice.authservice.entity.UserCredential;
import com.microservice.authservice.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService service;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public String addNewUser(@RequestBody UserCredential credential) {
		return service.saveUser(credential);
	}

	@PostMapping("/getToken")
	public String getToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			return service.generateToken(authRequest.getUsername());
		} else {
			throw new RuntimeException("Invalid Access !!!!!");
		}
	}

	@PostMapping("/validate")
	public String validateToken(@RequestParam("token") String token) {
		service.validateToken(token);
		return "Token is Valid!!!";
	}
}
