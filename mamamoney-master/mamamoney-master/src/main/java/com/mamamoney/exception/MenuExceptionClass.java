package com.mamamoney.exception;

public class MenuExceptionClass extends Exception {

	private static final long serialVersionUID = 2015633643589905348L;
	
	private String message;
	
	public MenuExceptionClass(String message) {
		super(message);
		
		this.message = message;
	}
	
	@Override
	public String toString() {
		return message;
	}
}
