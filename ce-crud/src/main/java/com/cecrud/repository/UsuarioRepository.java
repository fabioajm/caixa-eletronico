package com.cecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cecrud.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByNome(String nome);

}
