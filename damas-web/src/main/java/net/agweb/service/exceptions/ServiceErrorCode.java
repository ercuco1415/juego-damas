package net.agweb.service.exceptions;

public enum ServiceErrorCode {

	TIME_OUT("timeout"),

	UNEXPECTED("unexpected");

	private final static String PREFIX = "error.service.";

	private String errorCode;

	ServiceErrorCode(String errorCode) {
		this.errorCode = PREFIX + errorCode;
	}

	public String toString() {
		return errorCode;
	}

}
