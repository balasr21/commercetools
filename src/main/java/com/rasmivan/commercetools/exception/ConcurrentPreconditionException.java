package com.rasmivan.commercetools.exception;


public class ConcurrentPreconditionException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3630040717052588117L;
	
	public ConcurrentPreconditionException(String message) {
		super(message);
	}

}
