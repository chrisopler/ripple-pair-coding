package com.ripple.trustline.application;

public class BadCreditValueException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3844871569699620758L;

	public BadCreditValueException(String message) {
		super(message);
	}

}
