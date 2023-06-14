package com.microservice.userservice.exceptions;

public class GenericException extends RuntimeException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public GenericException() {
		super();
	}

	/**
	 * Constructors
	 */
	public GenericException(String msg, Exception e) {
		super(msg, e);

	}

	public GenericException(String msg) {
		super(msg);

	}

}
