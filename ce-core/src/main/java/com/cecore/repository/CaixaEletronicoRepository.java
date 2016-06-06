package com.cecore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cecore.model.CaixaEletronico;

public interface CaixaEletronicoRepository extends JpaRepository<CaixaEletronico, Long>{

	CaixaEletronico findByIdUsuario(String nome);

}
