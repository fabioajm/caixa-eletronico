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

import com.cecore.model.CaixaEletronico;
import com.cecore.model.Nota;
import com.cecore.service.CaixaEletronicoService;

@RestController
@RequestMapping("caixaeletronico")
public class CaixaEletronicoController {
	
	@Autowired
	private CaixaEletronicoService caixaEletronicoService;
	
	@RequestMapping(path="/{nome}/sacar/{idUsuario}/{valor}",method=RequestMethod.GET)
	public List<Nota> sacar(@PathVariable String nome, @PathVariable Long idUsuario, @PathVariable Integer valor){
		return caixaEletronicoService.sacar(nome, idUsuario, valor);
	}
	
	@RequestMapping(path="/{nome}/depositar", method=RequestMethod.POST)
	public HttpEntity<?> depositar(@PathVariable String nome, @RequestBody List<Nota> notas){
		caixaEletronicoService.depositar(nome, notas);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public HttpEntity<?> criar(@RequestBody CaixaEletronico caixaEletronico){
		caixaEletronicoService.criar(caixaEletronico);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<CaixaEletronico> listar(){
		return caixaEletronicoService.findAll();
	}

}
