package com.cecrud.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Usuario {
	
	@Id
	@SequenceGenerator(name="usuario_seq_id", initialValue=1, allocationSize=100)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="usuario_seq_id")
	private Long id;
	private String nome;
	@OneToOne
	private CaixaEletronico caixaEletronico;
	
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
	public CaixaEletronico getCaixaEletronico() {
		return caixaEletronico;
	}
	public void setCaixaEletronico(CaixaEletronico caixaEletronico) {
		this.caixaEletronico = caixaEletronico;
	}
}
