package com.rasmivan.commercetools.exception;


public class OutdateStockException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3630040717052588117L;
	
	
	public OutdateStockException(String message) {
		super(message);
	}

}
