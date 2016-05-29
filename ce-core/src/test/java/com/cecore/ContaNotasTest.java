package com.cecore;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.cecore.exception.ValorInvalidoException;
import com.cecore.model.Nota;

public class ContaNotasTest {
	
	private ContaNotas contaNotas;

	@Before
	public void init(){
		Nota dez = new Nota(10,6);
		Nota vinte = new Nota(20,1);
		Nota cinquenta = new Nota(50, 10);
		Nota cem = new Nota(100,5);
		contaNotas = new ContaNotas(dez,vinte, cinquenta, cem);
	}
	
	@Test
	public void notasDisponiveis(){
		Nota[] notas =contaNotas.getNotasDisponiveis();
		assertEquals(4, notas.length);
		assertEquals(new Integer(100), notas[0].getValor());
		assertEquals(new Integer(10), notas[3].getValor());
	}

	@Test
	public void sacarDez() {
		assertEquals(new Integer(1), contaNotas.executar(10).get(10));		
		assertEquals(1, contaNotas.executar(10).size());
	}
	
	@Test
	public void sacarCinquenta() {
		assertEquals(new Integer(1), contaNotas.executar(50).get(50));		
		assertEquals(1, contaNotas.executar(50).size());		
	}
	
	@Test
	public void sacarTrinta() {
		Map<Integer, Integer> notas = contaNotas.executar(30);
		assertEquals(2, notas.size());
		assertEquals(new Integer(1), notas.get(20));		
		assertEquals(new Integer(1), notas.get(10));		
	}
	@Test
	public void sacarCentoENoventa() {
		Map<Integer, Integer> notas = contaNotas.executar(190);
		assertEquals(4, notas.size());
		assertEquals(new Integer(1), notas.get(100));		
		assertEquals(new Integer(1), notas.get(50));		
		assertEquals(new Integer(1), notas.get(20));		
		assertEquals(new Integer(2), notas.get(10));		
	}
	
	@Test
	public void sacarMilEOitenta() {
		Map<Integer, Integer> notas = contaNotas.executar(1080);
		assertEquals(4, notas.size());
		assertEquals(new Integer(5), notas.get(100));		
		assertEquals(new Integer(10), notas.get(50));		
		assertEquals(new Integer(1), notas.get(20));		
		assertEquals(new Integer(6), notas.get(10));		
	}
	
	@Test
	public void sacarCentoEVinte() {
		Nota dez = new Nota(10,6);
		Nota vinte = new Nota(20,1);
		Nota cinquenta = new Nota(50, 10);
		Nota cem = new Nota(100,0);
		contaNotas = new ContaNotas(dez,vinte, cinquenta, cem);
		Map<Integer, Integer> notas = contaNotas.executar(120);
		assertEquals(2, notas.size());
		assertEquals(new Integer(2), notas.get(50));		
		assertEquals(new Integer(1), notas.get(20));		
	}
	
	@Test(expected = ValorInvalidoException.class)
	public void sacarValorMaiorQueSaldo() {		
		contaNotas.executar(2000);
	}
	
	@Test(expected = ValorInvalidoException.class)
	public void sacarTrintaETrez() {
		contaNotas.executar(33);		
	}
	
	@Test(expected = ValorInvalidoException.class)
	public void sacarValorNegativo(){
		contaNotas.executar(-10);		
	}

}
