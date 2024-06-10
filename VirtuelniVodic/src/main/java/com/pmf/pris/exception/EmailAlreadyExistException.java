package com.pmf.pris.exception;

public class EmailAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Email se već koristi, probajte sa drugim adresom!";
	}
}
