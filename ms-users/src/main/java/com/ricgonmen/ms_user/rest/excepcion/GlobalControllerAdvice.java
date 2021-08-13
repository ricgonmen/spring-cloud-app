package com.ricgonmen.ms_user.rest.excepcion;

import java.util.Arrays;
import java.util.HashSet;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice()
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiError  apiError = new ApiError(status, ex.getMessage());
		return ResponseEntity.status(status).headers(headers).body(apiError);
	}
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers,
	    HttpStatus status, WebRequest request) {
		ApiError  apiError = new ApiError(status, ex.getParameterName() + " parameter is missing.");
		return ResponseEntity.status(status).headers(headers).body(apiError);
	}
	
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
			ApiError apiError = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, messages.toString());
			return ResponseEntity.badRequest().body(apiError);
		} catch (Exception e) {
			ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
			return ResponseEntity.internalServerError().body(apiError);		
		}
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
			ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getCause().getCause().getLocalizedMessage());
			return ResponseEntity.badRequest().body(apiError);
		} catch (Exception e) {
			ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
			return ResponseEntity.internalServerError().body(apiError);
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