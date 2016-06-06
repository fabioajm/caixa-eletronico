package com.cecore.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cecore.exception.ValorInvalidoException;

public class CaixaEletronicoTest {
	
	private CaixaEletronico caixaEletronico;

	@Before
	public void init(){
		caixaEletronico = new CaixaEletronico("Caixa Teste");
		Nota dez = new Nota(10,6);
		Nota vinte = new Nota(20,1);
		Nota cinquenta = new Nota(50, 10);
		Nota cem = new Nota(100,5);
		caixaEletronico.depositarNotas(Arrays.asList(dez, vinte, cinquenta, cem ));
	}
	
	@Test
	public void notasDisponiveis(){
		List<Nota> saldos =caixaEletronico.getNotas();
		assertEquals(4, saldos.size());
		assertEquals(new Integer(100), saldos.get(0).getValor());
		assertEquals(new Integer(10), saldos.get(3).getValor());
	}

	@Test
	public void sacarDez() {
		List<Nota> saque = caixaEletronico.sacar(10);
		assertEquals(new Integer(10), saque.get(0).getValor());
		assertEquals(new Integer(1), saque.get(0).getQuantidade());		
		assertEquals(1, saque.size());
		assertEquals(new Integer(5),caixaEletronico.quantidadeDaNota(10));
	}
	
	@Test
	public void sacarCinquenta() {
		List<Nota> saque = caixaEletronico.sacar(50);
		assertEquals(new Integer(50), saque.get(0).getValor());
		assertEquals(new Integer(1), saque.get(0).getQuantidade());		
		assertEquals(1, saque.size());	
		assertEquals(new Integer(9),caixaEletronico.quantidadeDaNota(50));
	}
	
	@Test
	public void sacarTrinta() {
		List<Nota> notas = caixaEletronico.sacar(30);
		assertEquals(2, notas.size());
		assertEquals(new Integer(20), notas.get(0).getValor());
		assertEquals(new Integer(1), notas.get(0).getQuantidade());		
		assertEquals(new Integer(10), notas.get(1).getValor());
		assertEquals(new Integer(1), notas.get(1).getQuantidade());		
	}
	@Test
	public void sacarCentoENoventa() {
		List<Nota> notas = caixaEletronico.sacar(190);
		assertEquals(4, notas.size());
		assertEquals(new Integer(100), notas.get(0).getValor());
		assertEquals(new Integer(1), notas.get(0).getQuantidade());		
		assertEquals(new Integer(50), notas.get(1).getValor());
		assertEquals(new Integer(1), notas.get(1).getQuantidade());		
		assertEquals(new Integer(20), notas.get(2).getValor());
		assertEquals(new Integer(1), notas.get(2).getQuantidade());	
		assertEquals(new Integer(10), notas.get(3).getValor());
		assertEquals(new Integer(2), notas.get(3).getQuantidade());		
	}
	
	@Test
	public void sacarMilEOitenta() {
		List<Nota> notas = caixaEletronico.sacar(1080);
		assertEquals(4, notas.size());
		assertEquals(new Integer(100), notas.get(0).getValor());
		assertEquals(new Integer(5), notas.get(0).getQuantidade());		
		assertEquals(new Integer(50), notas.get(1).getValor());
		assertEquals(new Integer(10), notas.get(1).getQuantidade());		
		assertEquals(new Integer(20), notas.get(2).getValor());
		assertEquals(new Integer(1), notas.get(2).getQuantidade());	
		assertEquals(new Integer(10), notas.get(3).getValor());
		assertEquals(new Integer(6), notas.get(3).getQuantidade());		
	}
	
	@Test
	public void sacarCentoEVinte() {
		caixaEletronico = new CaixaEletronico("Caixa Teste");
		caixaEletronico.depositarPorNota(10, 6);
		caixaEletronico.depositarPorNota(20, 1);
		caixaEletronico.depositarPorNota(50, 10);
		caixaEletronico.depositarPorNota(100, 0);
		List<Nota> notas = caixaEletronico.sacar(120);
		assertEquals(2, notas.size());
		
		assertEquals(new Integer(50), notas.get(0).getValor());
		assertEquals(new Integer(2), notas.get(0).getQuantidade());		
		assertEquals(new Integer(20), notas.get(1).getValor());
		assertEquals(new Integer(1), notas.get(1).getQuantidade());	
	}
	
	@Test(expected = ValorInvalidoException.class)
	public void sacarValorMaiorQueSaldo() {		
		caixaEletronico.sacar(2000);
	}
	
	@Test(expected = ValorInvalidoException.class)
	public void sacarTrintaETrez() {
		caixaEletronico.sacar(33);		
	}
	
	@Test(expected = ValorInvalidoException.class)
	public void sacarValorNegativo(){
		caixaEletronico.sacar(-10);		
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
