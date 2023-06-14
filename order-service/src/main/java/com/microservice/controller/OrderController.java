package com.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.microservice.entity.Order;
import com.microservice.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	// TODO: 23-05-2023 1) Validation 2)Logging 3)ExceptionHandling 4) Response
	// Entity
	@PostMapping
	public String placeNewOrder(@RequestBody Order order) {
		return orderService.placeOder(order);
	}

	@GetMapping("{orderId}")
	public String getOrder(@PathVariable String orderId) {
		return orderService.getOrder(orderId);
	}
}
