package com.cecore.exception;

public class CadastroDuplicadoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CadastroDuplicadoException() {
		super();
	}

	public CadastroDuplicadoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CadastroDuplicadoException(String message, Throwable cause) {
		super(message, cause);
	}

	public CadastroDuplicadoException(String message) {
		super(message);
	}

	public CadastroDuplicadoException(Throwable cause) {
		super(cause);
	}

}
