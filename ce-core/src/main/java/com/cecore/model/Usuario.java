package com.cecore.model;

public class Usuario {
	
	private Long id;
	private String nome;
	private Double saldo;
	
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
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public boolean temSaldo(double valor) {
		return this.saldo >= valor;
	}
	
	

}
