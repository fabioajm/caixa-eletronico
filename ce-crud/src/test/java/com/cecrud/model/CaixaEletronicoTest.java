package com.cecrud.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CaixaEletronicoTest {
	
	private CaixaEletronico caixaEletronico;

	@Before
	public void init(){
		NotaDisponivel dez = new NotaDisponivel(10,6);
		NotaDisponivel vinte = new NotaDisponivel(20,1);
		NotaDisponivel cinquenta = new NotaDisponivel(50, 10);
		NotaDisponivel cem = new NotaDisponivel(100,5);
		caixaEletronico = new CaixaEletronico("Teste", Arrays.asList(dez, vinte, cinquenta, cem ));
	}
	
	@Test
	public void notasDisponiveis(){
		List<NotaDisponivel> notas = caixaEletronico.getNotasDisponiveis();
		assertEquals(4, notas.size());
		assertEquals(new Integer(100), notas.get(0).getValor());
		assertEquals(new Integer(10), notas.get(3).getValor());
	}
	
	@Test
	public void dopositarNotaDeDez(){
		caixaEletronico = new CaixaEletronico();
		caixaEletronico.depositarPorNota(10, 10);
		assertEquals(new Integer(10), caixaEletronico.quantidadeDaNota(10));
	}
	@Test
	public void dopositarNotaDeDezEVinte(){
		caixaEletronico = new CaixaEletronico();
		caixaEletronico.depositarPorNota(10, 10);
		caixaEletronico.depositarPorNota(20, 5);
		assertEquals(new Integer(10), caixaEletronico.quantidadeDaNota(10));
		assertEquals(new Integer(5), caixaEletronico.quantidadeDaNota(20));
	}
	@Test
	public void dopositarNotaDeDezDuasVezes(){
		caixaEletronico = new CaixaEletronico();
		caixaEletronico.depositarPorNota(10, 10);
		caixaEletronico.depositarPorNota(10, 5);
		assertEquals(new Integer(15), caixaEletronico.quantidadeDaNota(10));
	}
	
	@Test
	public void obterValorTotoal(){
		caixaEletronico = new CaixaEletronico();
		caixaEletronico.depositarPorNota(10, 10);
		caixaEletronico.depositarPorNota(20, 5);
		assertEquals(new Integer(200), caixaEletronico.getSaldo());
	}
}
