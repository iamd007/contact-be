package com.evolnet.demo.exception;

import java.util.List;

public class ErrorResponse {
	
	private String message;
	List<String> details;
	public ErrorResponse(String message, List<String> details) {
		super();
		this.message = message;
		this.details = details;
	}
	
	
}
