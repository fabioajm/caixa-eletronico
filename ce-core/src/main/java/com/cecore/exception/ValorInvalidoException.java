package com.cecore.exception;

public class ValorInvalidoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValorInvalidoException() {
		super();
	}

	public ValorInvalidoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ValorInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValorInvalidoException(String message) {
		super(message);
	}

	public ValorInvalidoException(Throwable cause) {
		super(cause);
	}

}