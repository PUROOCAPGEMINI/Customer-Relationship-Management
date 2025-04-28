package com.crm.api_gateway.exception;

public class UnauthorizedRoleException extends RuntimeException {
	 
	public UnauthorizedRoleException(String message) {
		super(message);
	}
 
}