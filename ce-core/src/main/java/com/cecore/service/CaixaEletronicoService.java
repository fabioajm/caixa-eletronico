package com.cecore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cecore.exception.CadastroDuplicadoException;
import com.cecore.exception.CaixaEletronicoNotFoundException;
import com.cecore.exception.ValorInvalidoException;
import com.cecore.model.CaixaEletronico;
import com.cecore.model.Nota;
import com.cecore.model.Usuario;
import com.cecore.repository.CaixaEletronicoRepository;

@Service
public class CaixaEletronicoService {

	@Autowired
	private CaixaEletronicoRepository caixaEletronicoRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void depositar(String nome, List<Nota> notas) {
		CaixaEletronico ce = caixaEletronicoRepository.findByNome(nome);
		if(ce == null){
			ce  = new CaixaEletronico(nome);
		}
		ce.depositarNotas(notas);
		caixaEletronicoRepository.save(ce);
	}

	public List<Nota> sacar(String nome, Long idUsuario, int valor) {
		CaixaEletronico ce = caixaEletronicoRepository.findByNome(nome);
		if(ce == null){
			throw new CaixaEletronicoNotFoundException(nome);
		}
		Usuario usuario = restTemplate.getForObject("http://localhost:8082/usuarios/" + idUsuario, Usuario.class);
		if(usuario == null){
			throw new CaixaEletronicoNotFoundException("Usuario não encontrado: " + idUsuario);
		}
		if(usuario.temSaldo(valor)){
			throw new ValorInvalidoException("Saldo insuficiente: " + usuario.getNome());
		}
		List<Nota> notas = ce.sacar(valor);
		caixaEletronicoRepository.save(ce);
		return notas;
	}

	public void criar(CaixaEletronico caixaEletronico) {
		CaixaEletronico ce = caixaEletronicoRepository.findByNome(caixaEletronico.getNome());
		if(ce != null){
			throw new CadastroDuplicadoException("Já existe cadastro com o nome " + caixaEletronico.getNome());
		}
		caixaEletronicoRepository.save(caixaEletronico);
	}

	public List<CaixaEletronico> findAll() {
		return caixaEletronicoRepository.findAll();
	}

}
