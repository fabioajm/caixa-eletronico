package com.cecrud.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class CaixaEletronico {
	
	@Id
	@SequenceGenerator(name="caixa_eletronico_seq_id", initialValue=1, allocationSize=100)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="caixa_eletronico_seq_id")
	private Long id;
	@OneToMany
	private List<Nota> saldo;

	public List<Nota> getSaldo() {
		return saldo;
	}

	public void setSaldo(List<Nota> saldo) {
		this.saldo = saldo;
	}

}
