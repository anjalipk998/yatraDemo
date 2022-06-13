package com.yatra.demo.exception;

public class SafetyDataException extends Exception {

	private static final long serialVersionUID = 810743570140746637L;

	public SafetyDataException() {
		super();
	}

	public SafetyDataException(String message, String errorCode) {
		super(message);
	}

}
