package com.microservice.paymentservice.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "PAYMENT_TBL", schema = "payment_schema")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String payMode;
	private Double amount;
	private Date paidDate;
	private Integer userId;
	private String orderId;
	private String paymentStatus;
}
