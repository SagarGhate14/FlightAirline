package com.cg.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

//controller advice to handle all application-level exceptions in one place
@ControllerAdvice
public class GlobalException {

	// Handles custom business logic errors (like missing Flight ID)

	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<?> resourceNotFound(ResourceNotFound ex, WebRequest req) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), new Date(), req.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	// Catches all other unexpected system errors (NullPointer, Database down, etc.)

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandler(ResourceNotFound ex, WebRequest req) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), new Date(), req.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
