package com.evolnet.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordsNotFoundException extends RuntimeException {
	
	public RecordsNotFoundException(String message)
	{
		super(message);
	}

}
