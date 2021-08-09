package com.ricgonmen.ms_user.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice()
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	/*
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers,
	    HttpStatus status, WebRequest request) {
		String error = ex.getParameterName() + " parameter is missing.";
		return new ResponseEntity<Object>(new ErrorResponse<>(Arrays.asList(error)), HttpStatus.BAD_REQUEST);
	}
	*/
	
	/**
	 * Exception thrown when constrain is violanted (username = null).
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
		try {
			Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
			Set<String> messages = new HashSet<>(constraintViolations.size());
			messages.addAll(constraintViolations.stream()
			        .map(constraintViolation -> String.format("%s value '%s' %s", constraintViolation.getPropertyPath(),
			                constraintViolation.getInvalidValue(), constraintViolation.getMessage()))
			        .collect(Collectors.toList()));
			return ResponseEntity.badRequest().body(messages);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(Arrays.asList(ex.getMessage()));		}
	}
	
	/**
	 * Exception thrown when constrain is violanted (username = null).
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<?> handleConstraintViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
		try {
			return ResponseEntity.badRequest().body(ex.getCause().getCause().getLocalizedMessage());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(Arrays.asList(ex.getMessage()));
		}
	}
	
	
	/**
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
		try {
			return ResponseEntity.badRequest().body("No se encuentra la entidad");
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(Arrays.asList(ex.getMessage()));
		}
	}
}