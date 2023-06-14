package com.microservice.userservice.exceptionhandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.microservice.userservice.exceptions.GenericException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@Component
public class ExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		String errorCode = String.valueOf(new Date().getTime());
		log.error(" ====>>>> " + errorCode + "===>>> " + ex.getMessage() + "==>> " + getExceptionMessage(ex));
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
			errors.put("errorCode", errorCode);
			errors.put("error", getExceptionMessage(ex));
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(GenericException.class)
	public ResponseEntity<Map<String, String>> handleUserNotFoundException(GenericException ex) {
		String errorCode = String.valueOf(new Date().getTime());
		log.error(" ====>>>> " + errorCode + "===>>> " + ex.getMessage() + "==>> " + getExceptionMessage(ex));
		Map<String, String> errors = new HashMap<>();
		errors.put("ErrorMessage", ex.getMessage());
		errors.put("errorCode", errorCode);
		errors.put("error", getExceptionMessage(ex));
		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public String getExceptionMessage(Exception ex) {
		return ex.getCause() != null ? ex.getCause().getMessage() : null;
	}

}
