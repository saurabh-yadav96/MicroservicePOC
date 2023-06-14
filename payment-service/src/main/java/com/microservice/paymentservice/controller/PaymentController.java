package com.microservice.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.paymentservice.entity.Payment;
import com.microservice.paymentservice.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@GetMapping("/{orderId}")
	public Payment getByOrderId(@PathVariable String orderId) {
		return paymentService.getByOrderId(orderId);
	}

}
