package com.microservice.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private Integer id;
	private String Name;
	private String email;
	private String paymentMethod;
	private String srcAccount;
	private Double availableAmount;
}
