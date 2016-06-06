package com.cecrud.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.cecrud.exception.ValorInvalidoException;

@Entity
public class NotaDisponivel implements Comparable<NotaDisponivel>{
	
	@Id
	@SequenceGenerator(name="nota_seq_id", initialValue=1, allocationSize=100)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="nota_seq_id")
	private Long id;
	private Integer quantidade;
	private Integer valor;
	
	public NotaDisponivel() {}
	
	public NotaDisponivel(Integer valor, int quantidade) {
		setValor(valor); 
		setQuantidade(quantidade);
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
		if(quantidade < 0){
			throw new ValorInvalidoException("Quantidade não pode ser negativo");
		}
		this.quantidade = quantidade;
	}
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		if(valor < 0){
			throw new ValorInvalidoException("Valor não pode ser negativo");
		}
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
	public int compareTo(NotaDisponivel o) {
		return valor.compareTo(o.getValor());
	}
}
