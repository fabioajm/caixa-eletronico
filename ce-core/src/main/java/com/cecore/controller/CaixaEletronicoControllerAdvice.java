package com.cecore.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.cecore.exception.CaixaEletronicoNotFoundException;
import com.cecore.exception.ValorInvalidoException;
import com.cecore.model.Message;

@ControllerAdvice
public class CaixaEletronicoControllerAdvice {
	
	@ResponseBody
	@ExceptionHandler(ValorInvalidoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	Message valorInvalidoExceptionHandler(ValorInvalidoException ex) {
		return new Message("error", ex.getMessage());
	}
	@ResponseBody
	@ExceptionHandler(CaixaEletronicoNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	Message caixaEletronicoExceptionHandler(CaixaEletronicoNotFoundException ex) {
		return new Message("error", ex.getMessage());
	}

}
