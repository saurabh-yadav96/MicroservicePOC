package com.microservice.paymentservice.consumer;

import java.util.Date;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jdk.jfr.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.paymentservice.dto.Order;
import com.microservice.paymentservice.dto.User;
import com.microservice.paymentservice.entity.Payment;
import com.microservice.paymentservice.repository.PaymentRepository;

@Component
public class OrderProcessingConsumer {

	public static final String USER_SERVICE_URL = "http://USER-SERVICE/users/";
	@Autowired
	@Lazy
	private RestTemplate restTemplate;

	@Autowired
	private PaymentRepository repository;

	@KafkaListener(topics = "ORDER_PAYMENT_TOPIC")
	@CircuitBreaker(name = "paymentService",fallbackMethod = "getOrderDetails")
	public void processOrder(String orderJsonString) {
		try {
			Order order = new ObjectMapper().readValue(orderJsonString, Order.class);
			// Build Payment Request
			Payment payment = Payment.builder().payMode(order.getPaymentMode()).amount(order.getPrice())
					.paidDate(new Date()).userId(order.getUserId()).orderId(order.getOrderId()).build();
			if (payment.getPayMode().equals("COD")) {
				payment.setPaymentStatus("PENDING");
				// Do rest call to user service (Validation not required)
			} else {
				// validation is required
				User user = restTemplate.getForObject(USER_SERVICE_URL + payment.getUserId(), User.class);
				if (payment.getAmount() > (user.getAvailableAmount())) {
					throw new RuntimeException("Insufficient Ammount !!!");
				} else {
					payment.setPaymentStatus("PAID");
					repository.save(payment);
					restTemplate.put(USER_SERVICE_URL + "/" + payment.getUserId() + "/"
							+ (user.getAvailableAmount() - payment.getAmount()), null);
				}
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			// log
		}
	}
	public String getOrderDetails(Exception ex)
	{
		//Fall back method to do anything related to fall back
		return ex.getMessage()+"Circuit Breaker works!!!!!!";
	}
}
