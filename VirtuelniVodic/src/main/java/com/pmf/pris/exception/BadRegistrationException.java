package com.pmf.pris.exception;

public class BadRegistrationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Šifra i šifra za proveru nije isti!";
	}
}
