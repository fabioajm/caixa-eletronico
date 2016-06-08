package com.cecore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Nota implements Comparable<Nota>{
	
	@Id
	@SequenceGenerator(name="nota_seq_id", initialValue=1, allocationSize=100)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="nota_seq_id")
	private Long id;
	private Integer quantidade;
	private Double valor;
	
	public Nota() {}
	
	public Nota(Double valor, int quantidade) {
		this.valor = valor;
		this.quantidade = quantidade;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public void subtrairQuantidade(Integer quantidade){
		this.quantidade-=quantidade;
	}

	public void adicionarQuantidade(Integer quantidade) {
		this.quantidade +=quantidade;
	}

	public Double total() {
		if(quantidade == null || valor == null){
			return 0.0;
		}
		return quantidade * valor;
	}
	
	@Override
	public int compareTo(Nota o) {
		return valor.compareTo(o.getValor());
	}
}
