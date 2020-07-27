package com.dasa.labex.exception;

import lombok.Getter;

public class InternalServerException extends RuntimeException {

	private static final long serialVersionUID = 5249489335694483687L;
	
	private static final String  ERROR = "Internal Server Error";
	private static final String  MESSAGE = "Erro interno ao servidor";
	
	@Getter
	private final String error;
	
	public InternalServerException() {
		super(MESSAGE);
		this.error = ERROR;
	}
	
	public InternalServerException(String message) {
		super(message);
		this.error = ERROR;
	}
	
	public InternalServerException(Throwable throwable) {
		super(MESSAGE,throwable);
		this.error = ERROR;
	}
}