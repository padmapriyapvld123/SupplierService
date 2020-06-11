package com.nineleaps.supplier.exception;

public class UserException extends Exception {

	private static final long serialVersionUID = 3548521210455666819L;

	public UserException(String message) {
		super(message);
	}

	public UserException(String message, Throwable th) {
		super(message, th);
	}
}