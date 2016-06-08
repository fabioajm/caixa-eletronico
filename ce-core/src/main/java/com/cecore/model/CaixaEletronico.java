package com.cecore.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.cecore.exception.ValorInvalidoException;

@Entity
public class CaixaEletronico {
	
	@Id
	@SequenceGenerator(name="caixa_seq_id", initialValue=1, allocationSize=100)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="caixa_seq_id")
	private Long id;
	@Column(unique=true)
	private String  nome;
	@OneToMany(cascade=CascadeType.ALL)
	List<Nota> notas = new ArrayList<>();
	
	public CaixaEletronico() {
	}

	public CaixaEletronico(String nome) {
		this.nome = nome;
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
	public List<Nota> getNotas() {
		Collections.sort(notas, Collections.reverseOrder());
		return notas;
	}
	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}
	
	public List<Nota> sacar(int valor) {
		verificarSeValorEMultiploDeDez(valor);
		
		List<Nota> notas = new ArrayList<>();
		
		for (Nota saldo : getNotas()) {
			int qtd = (int) (valor / saldo.getValor());
			if (qtd == 0 || saldo.getQuantidade() == 0){
				continue;
			}
			if (qtd > saldo.getQuantidade()) {
				qtd = saldo.getQuantidade();
			}
			notas.add(new Nota(saldo.getValor(), qtd));
			saldo.subtrairQuantidade(qtd);
			valor -= saldo.getValor() * qtd;
		}
		
		if (valor > 0) {
			throw new ValorInvalidoException("Saldo insuficiente no caixa");
		}
		return notas;
	}

	private void verificarSeValorEMultiploDeDez(double valor) {
		if (valor <= 0 || valor % 10 != 0) {
			throw new ValorInvalidoException(
					"Os valores devem ser multiplos de 10" );
		}
	}
	
	public void depositarPorNota(double valorNota, Integer quantidade){
		if(notas == null){
			notas = new ArrayList<>();
		}
		Nota nota = null;
		for (Nota n : notas) {
			if(n.getValor().equals(valorNota)){
				nota = n;
			}
		}
		if(nota != null){
			nota.adicionarQuantidade(quantidade);
		}else{
			notas.add(new Nota(valorNota, quantidade));
		}
	}

	public Integer quantidadeDaNota(double valorNota) {
		for(Nota nota: notas) {
			if(nota.getValor().equals(valorNota)){
				return nota.getQuantidade();
			}
		}
		return 0;
	}
	public void depositarNotas(List<Nota> notas){
		for (Nota nota : notas) {
			depositarPorNota(nota.getValor(), nota.getQuantidade());
		}
	}

	public Double getSaldo() {
		double saldo = 0;
		for (Nota nota : notas) {
			saldo +=  nota.total();
		}
		return saldo;
	}

}
