package com.cecrud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Nota implements Comparable<Nota>{

	@Id
	private Integer valor;
	
	@Column(length=10000)
	private byte[] imagem;
	 
	public Nota() {
	}

	public Nota(Integer valor) {
		this.valor = valor;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	@Override
	public int compareTo(Nota o) {
		return valor.compareTo(o.getValor());
	}

}
