package com.cecore.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cecore.model.Nota;

@RestController
@RequestMapping("/notas")
public class ContarNotasController {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Nota> notas(){
		List<Nota> notas = new ArrayList<>();
		notas.add(new Nota(20, 5));
		notas.add(new Nota(10, 3));
		
		return notas;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public HttpEntity<?> contar(@RequestBody List<Nota> notas){
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
