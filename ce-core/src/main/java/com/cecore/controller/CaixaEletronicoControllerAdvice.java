package com.cecore.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.cecore.exception.CadastroDuplicadoException;
import com.cecore.exception.CaixaEletronicoNotFoundException;
import com.cecore.exception.ValorInvalidoException;
import com.cecore.model.Message;

@ControllerAdvice
public class CaixaEletronicoControllerAdvice {
	
	@ResponseBody
	@ExceptionHandler(ValorInvalidoException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	Message valorInvalidoExceptionHandler(ValorInvalidoException ex) {
		return new Message("error", "Valor inválido:" + ex.getMessage());
	}
	@ResponseBody
	@ExceptionHandler(CaixaEletronicoNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	Message caixaEletronicoExceptionHandler(CaixaEletronicoNotFoundException ex) {
		return new Message("error", "Caixa Eletrônico não encontrado:" + ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(CadastroDuplicadoException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	Message cadastroDuplicadoExceptionHandler(CadastroDuplicadoException ex) {
		return new Message("error", "Cadastro duplicado:" + ex.getMessage());
	}

}
