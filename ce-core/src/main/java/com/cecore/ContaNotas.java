package com.cecore;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.cecore.exception.ValorInvalidoException;
import com.cecore.model.Nota;

public class ContaNotas {

	private Nota[] notasDisponiveis;

	public ContaNotas(Nota... notasDisponiveis) {
		this.notasDisponiveis = notasDisponiveis;
		Arrays.sort(this.notasDisponiveis, Collections.reverseOrder());
	}

	public Map<Integer, Integer> executar(double valor) {
		verificarSeValorEMultiploDaMenorNota(valor);
		
		Map<Integer, Integer> notas = new HashMap<>();
		
		for (Nota nota : notasDisponiveis) {
			int qtd = (int) (valor / nota.getValor());
			if (qtd == 0 || nota.getQuantidade() == 0){
				continue;
			}
			if (qtd > nota.getQuantidade()) {
				qtd = nota.getQuantidade();
			}
			notas.put(nota.getValor(), qtd);
			valor -= nota.getValor() * qtd;
		}
		
		if (valor > 0) {
			throw new ValorInvalidoException("Saldo insuficiente");
		}
		return notas;
	}

	private void verificarSeValorEMultiploDaMenorNota(double valor) {
		Nota menorNota = notasDisponiveis[notasDisponiveis.length - 1];
		if (valor <= 0 || valor % menorNota.getValor() != 0) {
			throw new ValorInvalidoException(
					"Os valores devem ser multiplos de " + menorNota.getValor());
		}
	}

	public Nota[] getNotasDisponiveis() {
		return notasDisponiveis;
	}

}
