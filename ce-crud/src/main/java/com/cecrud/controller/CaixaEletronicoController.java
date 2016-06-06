package com.cecrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cecrud.model.NotaDisponivel;
import com.cecrud.service.CaixaEletronicoService;


@RestController
@RequestMapping("/caixaeletronico")
public class CaixaEletronicoController {
	
	@Autowired
	private CaixaEletronicoService caixaEletronicoService;
	
	@RequestMapping(path="/{nome}", method=RequestMethod.POST)
	public HttpEntity<?> depositar(@PathVariable String nome, @RequestBody List<NotaDisponivel> notas){
		caixaEletronicoService.save(nome, notas);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	

}
