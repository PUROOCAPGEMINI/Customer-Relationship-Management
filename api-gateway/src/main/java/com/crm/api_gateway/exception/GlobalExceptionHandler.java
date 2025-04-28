package com.crm.api_gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
 
import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException ex){
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
	}
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<String> handleInvalidTokenException(InvalidTokenException ex){
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
	}
	@ExceptionHandler(UnauthorizedRoleException.class)
	public ResponseEntity<String> handleUnauthorizedRoleException(UnauthorizedRoleException ex){
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGenericException(UnauthorizedRoleException ex){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occured");
	}
 
}
