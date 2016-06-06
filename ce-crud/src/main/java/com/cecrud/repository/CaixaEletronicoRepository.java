package com.cecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cecrud.model.CaixaEletronico;

public interface CaixaEletronicoRepository extends JpaRepository<CaixaEletronico, Long>{

	CaixaEletronico findByNome(String nome);

}
