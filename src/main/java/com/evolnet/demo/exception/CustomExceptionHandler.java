package com.evolnet.demo.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler  extends ResponseEntityExceptionHandler{

	
	
	@ExceptionHandler(RecordsNotFoundException.class)
	public final ResponseEntity<Object> handleRecordNotFoundException( RecordsNotFoundException ex,
			                                    WebRequest request)
	{
	  List<String> details = new ArrayList();
	  details.add(ex.getLocalizedMessage());
	  ErrorResponse error = new ErrorResponse("Record not found", details);
	  return new ResponseEntity(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handlesAllExceptions(Exception ex, WebRequest request)
	{
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("ServerError",details);
		return new ResponseEntity(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
