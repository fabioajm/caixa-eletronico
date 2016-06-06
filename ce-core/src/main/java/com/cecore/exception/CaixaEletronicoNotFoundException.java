package com.cecore.exception;

public class CaixaEletronicoNotFoundException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CaixaEletronicoNotFoundException() {
		super();
	}

	public CaixaEletronicoNotFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CaixaEletronicoNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CaixaEletronicoNotFoundException(String message) {
		super(message);
	}

	public CaixaEletronicoNotFoundException(Throwable cause) {
		super(cause);
	}

}
