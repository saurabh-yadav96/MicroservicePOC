package com.microservice.paymentservice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

	private Integer id;
	private String name;
	private String category;
	private Double price;
	private Date purchaseDate;
	private String orderId;
	private Integer userId;
	private String paymentMode;
}
