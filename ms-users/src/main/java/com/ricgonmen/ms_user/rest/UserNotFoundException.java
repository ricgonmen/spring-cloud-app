package com.ricgonmen.ms_user.rest;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1767080731793773585L;

	UserNotFoundException(Long id) {
	    super("Could not find employee with id = " + id);
	  }
	
	UserNotFoundException(String username) {
	    super("Could not find employee with username = " + username);
	  }
}