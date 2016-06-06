package com.cecrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cecrud.exception.CadastroDuplicadoException;
import com.cecrud.model.CaixaEletronico;
import com.cecrud.model.NotaDisponivel;
import com.cecrud.repository.CaixaEletronicoRepository;

@Service
public class CaixaEletronicoService {
	
	@Autowired
	private CaixaEletronicoRepository repository;

	public void save(String nome, List<NotaDisponivel> notas) {
		CaixaEletronico ce = repository.findByNome(nome);
		if(ce != null){
			throw new CadastroDuplicadoException(nome);
		}
		ce = new CaixaEletronico(nome, notas);
		repository.save(ce);
	}

}
