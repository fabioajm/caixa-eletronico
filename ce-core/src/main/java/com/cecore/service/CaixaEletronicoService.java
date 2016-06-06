package com.cecore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cecore.exception.CaixaEletronicoNotFoundException;
import com.cecore.model.CaixaEletronico;
import com.cecore.model.Nota;
import com.cecore.repository.CaixaEletronicoRepository;

@Service
public class CaixaEletronicoService {

	@Autowired
	private CaixaEletronicoRepository caixaEletronicoRepository;
	
	public void depositar(String idUsuario, List<Nota> notas) {
		CaixaEletronico ce = caixaEletronicoRepository.findByIdUsuario(idUsuario);
		if(ce == null){
			ce  = new CaixaEletronico(idUsuario);
		}
		ce.depositarNotas(notas);
		caixaEletronicoRepository.save(ce);
	}

	public List<Nota> sacar(String idUsuario, int valor) {
		CaixaEletronico ce = caixaEletronicoRepository.findByIdUsuario(idUsuario);
		if(ce == null){
			throw new CaixaEletronicoNotFoundException(idUsuario);
		}
		List<Nota> notas = ce.sacar(valor);
		caixaEletronicoRepository.save(ce);
		return notas;
	}
}
