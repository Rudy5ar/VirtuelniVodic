package com.pmf.pris.exception;

public class BadLoginException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BadLoginException(String msg) {
		super(msg);
	}
}
