package com.cecrud.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cecrud.model.NotaDisponivel;

public class NotaTest {

	@Test
	public void deveSubtrairDezDaQauntidade() {
		NotaDisponivel nota = new NotaDisponivel(10,20);
		nota.subtrairQuantidade(10);
		assertEquals(new Integer(10), nota.getQuantidade());
	}

	@Test
	public void deveAdicionarDezDaQauntidade() {
		NotaDisponivel nota = new NotaDisponivel(10,20);
		nota.adicionarQuantidade(10);
		assertEquals(new Integer(30), nota.getQuantidade());
	}

	@Test
	public void totalNota(){
		NotaDisponivel nota = new NotaDisponivel(100, 20);
		assertEquals(new Integer(2000), nota.total());
	}
	
	@Test
	public void totalNotaComQuantidadeNula(){
		NotaDisponivel nota = new NotaDisponivel();
		nota.setValor(100);
		assertEquals(new Integer(0), nota.total());
	}
	
	@Test
	public void totalNotaComValorNulo(){
		NotaDisponivel nota = new NotaDisponivel();
		nota.setQuantidade(100);
		assertEquals(new Integer(0), nota.total());
	}
}

