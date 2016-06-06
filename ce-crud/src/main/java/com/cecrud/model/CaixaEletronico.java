package com.cecrud.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CaixaEletronico {

	@Id
	@GeneratedValue
	private Long id;
	@Column(unique=true)
	private String nome;
	@OneToMany(cascade=CascadeType.ALL)
	private List<NotaDisponivel> notasDisponiveis = new ArrayList<>();
	
	public CaixaEletronico() {
	}
	
	public CaixaEletronico(String nome, List<NotaDisponivel> notasDisponiveis) {
		this.nome = nome;
		depositarNotas(notasDisponiveis);
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
	public List<NotaDisponivel> getNotasDisponiveis() {
		Collections.sort(notasDisponiveis, Collections.reverseOrder());
		return notasDisponiveis;
	}
	public void setNotasDisponiveis(List<NotaDisponivel> notasDisponiveis) {
		this.notasDisponiveis = notasDisponiveis;
	}
	public void depositarPorNota(Integer valorNota, Integer quantidade){
		NotaDisponivel nota = null;
		for (NotaDisponivel n : notasDisponiveis) {
			if(n.getValor().equals(valorNota)){
				nota = n;
			}
		}
		if(nota != null){
			nota.adicionarQuantidade(quantidade);
		}else{
			notasDisponiveis.add(new NotaDisponivel(valorNota, quantidade));
		}
	}

	public Integer quantidadeDaNota(int valorNota) {
		for(NotaDisponivel nota: notasDisponiveis) {
			if(nota.getValor().equals(valorNota)){
				return nota.getQuantidade();
			}
		}
		return 0;
	}
	public void depositarNotas(List<NotaDisponivel> notas){
		for (NotaDisponivel nota : notas) {
			depositarPorNota(nota.getValor(), nota.getQuantidade());
		}
	}
	
	public Integer getSaldo() {
		Integer saldo = 0;
		for (NotaDisponivel nota : notasDisponiveis) {
			saldo +=  nota.total();
		}
		return saldo;
	}


}
