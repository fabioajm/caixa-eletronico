package com.cecore.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class NotaTest {

	@Test
	public void deveSubtrairDezDaQauntidade() {
		Nota nota = new Nota(10.0,20);
		nota.subtrairQuantidade(10);
		assertEquals(new Integer(10), nota.getQuantidade());
	}

	@Test
	public void deveAdicionarDezDaQauntidade() {
		Nota nota = new Nota(10.0,20);
		nota.adicionarQuantidade(10);
		assertEquals(new Integer(30), nota.getQuantidade());
	}

	@Test
	public void totalNota(){
		Nota nota = new Nota(100.0, 20);
		assertEquals(new Double(2000.0), nota.total());
	}
	
	@Test
	public void totalNotaComQuantidadeNula(){
		Nota nota = new Nota();
		nota.setValor(100.0);
		assertEquals(new Double(0.0), nota.total());
	}
	
	@Test
	public void totalNotaComValorNulo(){
		Nota nota = new Nota();
		nota.setQuantidade(100);
		assertEquals(new Double(0.0), nota.total());
	}
}

