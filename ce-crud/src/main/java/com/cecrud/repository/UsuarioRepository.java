package com.cecrud.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cecrud.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByNome(String nome);
	
	@Query("select u from Usuario u where UPPER(u.nome) like UPPER(?)")
    Collection<Usuario> search(String term);

}
