package com.microservice.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.entity.Order;
import com.microservice.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Value("${order.producer.topic.name}")
	private String topicName;

	public String placeOder(Order order) {
		// save a copy in order-service DB
		order.setPurchaseDate(new Date());
		order.setOrderId(UUID.randomUUID().toString().split("-")[0]);
		repository.save(order);
		// send it to payment service using Kafka
		try {
			kafkaTemplate.send(topicName, new ObjectMapper().writeValueAsString(order));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			// todo Log here
			//change the exception handler
		}
		return "Your order with " + order.getOrderId() + " has been placed ! we will notify once it is confirmed";
	}

	public String getOrder(String orderId) {
		return null;
	}

}
