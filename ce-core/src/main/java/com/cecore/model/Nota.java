package com.cecore.model;

public class Nota implements Comparable<Nota>{

	private Integer valor;
	private Integer quantidade;
	 
	public Nota() {
	}

	public Nota(Integer valor, Integer quantidade) {
		this.valor = valor;
		this.quantidade = quantidade;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int compareTo(Nota o) {
		return valor.compareTo(o.getValor());
	}

}
