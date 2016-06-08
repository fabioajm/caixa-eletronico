package com.cecrud.service;

import java.util.Collection;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cecrud.exception.CadastroDuplicadoException;
import com.cecrud.model.Usuario;
import com.cecrud.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;

	public String save(Usuario usuario) {
		Usuario u = repository.findByNome(usuario.getNome());
		if(u != null){
			throw new CadastroDuplicadoException(usuario.getNome());
		}
		repository.save(usuario);
		return usuario.getNome();
	}

	public Collection<Usuario> findAll() {
		return repository.findAll();
	}

	public Usuario findOne(Long id) {
		return repository.findOne(id);
	}
	public Collection<Usuario> search(String term){
		return repository.search(term);
	}

	public void sacar(Long id, Double valor) {
		Usuario u = repository.findOne(id);
		if(u == null){
			throw new ValidationException("Usuario não encontrado:" + id);
		}
		u.subtrairSaldo(valor);
		repository.save(u);
	}

}
