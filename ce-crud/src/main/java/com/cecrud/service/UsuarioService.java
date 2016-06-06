package com.cecrud.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cecrud.exception.CadastroDuplicadoException;
import com.cecrud.model.Usuario;
import com.cecrud.model.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;

	public String save(Usuario usuario) {
		Usuario u = repository.findOne(usuario.getNome());
		if(u != null){
			throw new CadastroDuplicadoException(usuario.getNome());
		}
		repository.save(usuario);
		return usuario.getNome();
	}

	public Collection<Usuario> findAll() {
		return repository.findAll();
	}

}
