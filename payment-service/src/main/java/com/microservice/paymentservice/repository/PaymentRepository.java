package com.microservice.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.paymentservice.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	Payment findByOrderId(String orderId);
}
