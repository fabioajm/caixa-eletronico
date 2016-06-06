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
	private Integer valor;
	
	public Nota() {}
	
	public Nota(Integer valor, int quantidade) {
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
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	public void subtrairQuantidade(Integer quantidade){
		this.quantidade-=quantidade;
	}

	public void adicionarQuantidade(Integer quantidade) {
		this.quantidade +=quantidade;
	}

	public Integer total() {
		if(quantidade == null || valor == null){
			return 0;
		}
		return quantidade * valor;
	}
	
	@Override
	public int compareTo(Nota o) {
		return valor.compareTo(o.getValor());
	}
}
