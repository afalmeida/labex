package com.dasa.labex.exception;

public class NotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 8848112773936020205L;
	
	private static final String  MESSAGE = "Recurso não encontrado";

	public NotFoundException() {
		super(MESSAGE);
	}
	
	public NotFoundException(Throwable throwable) {
		super(MESSAGE,throwable);
	}
}