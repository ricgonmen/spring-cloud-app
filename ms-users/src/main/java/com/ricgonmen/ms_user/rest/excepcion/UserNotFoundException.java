package com.ricgonmen.ms_user.rest.excepcion;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4295489939985047236L;

	public UserNotFoundException(String username) {
	    super("Could not find employee with username = " + username);
	  }
}