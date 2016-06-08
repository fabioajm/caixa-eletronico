package com.cecore.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cecore.CeCoreApplication;
import com.cecore.exception.CadastroDuplicadoException;
import com.cecore.exception.CaixaEletronicoNotFoundException;
import com.cecore.model.CaixaEletronico;
import com.cecore.model.Nota;
import com.cecore.repository.CaixaEletronicoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CeCoreApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
@Transactional
public class CaixaEletronicoServiceTest {
	
	@Autowired
	private CaixaEletronicoService caixaEletronicoService;
	
	/*@Mock
	private RestTemplate restTemplate;*/
	
	@Autowired
	private CaixaEletronicoRepository repository;
	
	private String nome;
	
	@Before
	public void init(){
		repository.deleteAllInBatch();
		nome = "caixa centro";
	}
	
	@Test
	public void criar() {
		List<Nota> notas = new ArrayList<>();
		notas.add(new Nota(10., 2));
		notas.add(new Nota(50.0, 1));
		CaixaEletronico ce = new CaixaEletronico(nome);
		ce.depositarNotas(notas);
		caixaEletronicoService.criar(ce);
		assertEquals(new Double(70.0), repository.findByNome(nome).getSaldo());
		
	}
	
	@Test(expected=CadastroDuplicadoException.class)
	public void criarDuasVezes() {
		List<Nota> notas = new ArrayList<>();
		notas.add(new Nota(10.0, 2));
		notas.add(new Nota(50.0, 1));
		CaixaEletronico ce = new CaixaEletronico(nome);
		caixaEletronicoService.criar(ce);
		caixaEletronicoService.criar(ce);
		assertEquals(new Integer(70), repository.findByNome(nome).getSaldo());
		
	}
	
	@Test
	public void depositarCem() {
		List<Nota> notas = new ArrayList<>();
		notas.add(new Nota(10.0, 2));
		notas.add(new Nota(50.0, 1));
		caixaEletronicoService.depositar(nome, notas );
		assertEquals(new Double(70.0), repository.findByNome(nome).getSaldo());
		
	}
	@Test
	public void depositarDuasVezes() {
		List<Nota> notas = new ArrayList<>();
		notas.add(new Nota(10.0, 2));
		notas.add(new Nota(50.0, 1));
		caixaEletronicoService.depositar(nome, notas );
		assertEquals(new Double(70.0), repository.findByNome(nome).getSaldo());
		caixaEletronicoService.depositar(nome, notas );
		assertEquals(new Double(140.0), repository.findByNome(nome).getSaldo());
		
	}

	@Ignore("not ready yet, need mock resttemplate") 
	@Test
	public void sacarMil() {
		Long idUsuario = 4l;
		List<Nota> notas = new ArrayList<>();
		notas.add(new Nota(100.0, 9));
		notas.add(new Nota(50.0, 2));
		notas.add(new Nota(10.0, 5));
		caixaEletronicoService.depositar(nome, notas );
		caixaEletronicoService.sacar(nome, idUsuario,  1000 );
		assertEquals(new Integer(50), repository.findByNome(nome).getSaldo());
	}
	
	@Test(expected=CaixaEletronicoNotFoundException.class)
	public void sacarContaInexistente() {
		Long idUsuario = 1l;
		caixaEletronicoService.sacar(nome, idUsuario, 1000 );
	}

}
