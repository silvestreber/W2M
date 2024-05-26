package org.w2m.w2m.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.w2m.w2m.exception.NaveNoEncontradaException;
import org.w2m.w2m.util.ConstantesError;

import jakarta.persistence.PersistenceException;

@ControllerAdvice
public class ExcepcionController {

	@ExceptionHandler(NaveNoEncontradaException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleNaveNotFoundException(NaveNoEncontradaException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PersistenceException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public ResponseEntity<String> handlePersistenceException(PersistenceException e) {
		return new ResponseEntity<>(ConstantesError.ERROR_BBDD, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> handleGeneralException(Exception e) {
		return new ResponseEntity<>(ConstantesError.ERROR_GENERICO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
