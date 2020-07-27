package com.dasa.labex.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dasa.labex.exception.BadRequestException;
import com.dasa.labex.exception.InternalServerException;
import com.dasa.labex.exception.NotFoundException;
import com.dasa.labex.exception.Error;
import com.dasa.labex.exception.UnprocessableEntityException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Error> BadRequestError(BadRequestException ex, WebRequest request) {

		Error error = Error
				.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.error(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.message(ex.getMessage())
				.validationErrors(ex.getValidationErrors())
				.build();

		return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Error> notFoundError(Exception ex, WebRequest request) {

		Error error = Error
				.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.NOT_FOUND.value())
				.error(HttpStatus.NOT_FOUND.getReasonPhrase())
				.message(ex.getMessage())
				.build();

		return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UnprocessableEntityException.class)
	public ResponseEntity<Error> UnprocessableEntityError(UnprocessableEntityException ex, WebRequest request) {

		Error error = Error
				.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.UNPROCESSABLE_ENTITY.value())
				.error(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase())
				.message(ex.getMessage())
				.validationErrors(ex.getValidationErrors())
				.build();

		return new ResponseEntity<Error>(error, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(InternalServerException.class)
	public ResponseEntity<Error> internalServerError(Exception ex, WebRequest request) {

		Error error = Error
				.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.message(ex.getMessage())
				.build();
		
		ex.printStackTrace();

		return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<Error>  handleMethodArgumentNotValid(MissingRequestHeaderException ex, WebRequest request) {
             
    	Error error = Error
				.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.error(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.message(ex.getMessage())
				//.validationErrors(ex.getValidationErrors())
				.build();

		return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
    }
    
	
	 @Override
	   protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
			Error error = Error
					.builder()
					.timestamp(LocalDateTime.now())
					.status(HttpStatus.BAD_REQUEST.value())
					.error(HttpStatus.BAD_REQUEST.getReasonPhrase())
					.message(ex.getMessage())
					.build();

			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	   }

    
	 @Override
	   protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
			Error error = Error
					.builder()
					.timestamp(LocalDateTime.now())
					.status(HttpStatus.BAD_REQUEST.value())
					.error(HttpStatus.BAD_REQUEST.getReasonPhrase())
					.message(ex.getMessage())
					.build();

			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	   }
}