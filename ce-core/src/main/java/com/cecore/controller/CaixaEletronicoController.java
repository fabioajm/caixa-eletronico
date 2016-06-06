package com.cecore.controller;

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

import com.cecore.model.Nota;
import com.cecore.service.CaixaEletronicoService;

@RestController
public class CaixaEletronicoController {
	
	@Autowired
	private CaixaEletronicoService caixaEletronicoService;
	
	@RequestMapping(path="/sacar/{idUsuario}/{valor}",method=RequestMethod.GET)
	public List<Nota> sacar(@PathVariable String idUsuario, @PathVariable Integer valor){
		return caixaEletronicoService.sacar(idUsuario, valor);
	}
	
	@RequestMapping(path="/depositar/{idUsuario}", method=RequestMethod.POST)
	public HttpEntity<?> depositar(@PathVariable String idUsuario, @RequestBody List<Nota> notas){
		caixaEletronicoService.depositar(idUsuario, notas);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
