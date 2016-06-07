package com.cecrud.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.cecrud.exception.ValorInvalidoException;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private Integer saldo;

	public Usuario() {
	}

	public Usuario(String nome, Integer saldo) {
		setNome(nome);
		setSaldo(saldo);
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public void setSaldo(Integer saldo) {
		if(saldo < 0){
			throw new ValorInvalidoException("Saldo não pode ser menor que 0") ;
		}
		this.saldo = saldo;
	}
}
