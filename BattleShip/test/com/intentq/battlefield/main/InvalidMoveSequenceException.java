package com.intentq.battlefield.main;

public class InvalidMoveSequenceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6338372729211053386L;
	
	InvalidMoveSequenceException(String message) {
		super(message);
	}
}
