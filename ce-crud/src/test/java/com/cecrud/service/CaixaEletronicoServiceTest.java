package com.cecrud.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cecrud.CeCrudApplication;
import com.cecrud.exception.CadastroDuplicadoException;
import com.cecrud.model.CaixaEletronico;
import com.cecrud.model.NotaDisponivel;
import com.cecrud.repository.CaixaEletronicoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CeCrudApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
@Transactional
public class CaixaEletronicoServiceTest {
	
	@Autowired
	private CaixaEletronicoService caixaEletronicoService;
	
	@Autowired
	private CaixaEletronicoRepository repository;
	
	private String nome;
	
	@Before
	public void init(){
		repository.deleteAllInBatch();
		nome = "Centro";
	}
	
	@Test
	public void salvar(){
		NotaDisponivel cinquenta = new NotaDisponivel(50, 10);
		NotaDisponivel cem = new NotaDisponivel(100, 20);
		caixaEletronicoService.save(nome, Arrays.asList(cinquenta, cem));
		CaixaEletronico ce = repository.findByNome(nome);
		assertEquals(new Integer(2500), ce.getSaldo());
	}
	@Test(expected=CadastroDuplicadoException.class)
	public void salvarDoisCaixasNomesIguais(){
		NotaDisponivel cinquenta = new NotaDisponivel(50, 10);
		NotaDisponivel cem = new NotaDisponivel(100, 20);
		caixaEletronicoService.save(nome, Arrays.asList(cinquenta, cem));
		caixaEletronicoService.save(nome, Arrays.asList(cinquenta, cem));
	}
	
	
}
