package com.cecrud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cecrud.exception.CadastroDuplicadoException;
import com.cecrud.exception.ValorInvalidoException;
import com.cecrud.model.Message;

@ControllerAdvice
public class CecrudControllerAdvice {
	
	@ResponseBody
	@ExceptionHandler(ValorInvalidoException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	Message valorInvalidoExceptionHandler(ValorInvalidoException ex) {
		return new Message("error", ex.getMessage());
	}
	@ResponseBody
	@ExceptionHandler(CadastroDuplicadoException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	Message caixaEletronicoExceptionHandler(CadastroDuplicadoException ex) {
		return new Message("error", ex.getMessage());
	}

}
