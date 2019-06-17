package com.ripple.trustline.application;

public class BadDebitValueException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5624663108506603317L;

	public BadDebitValueException(String message) {
		super(message);
	}
	

}
