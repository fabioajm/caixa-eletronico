package com.cecrud.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.cecrud.CeCrudApplication;
import com.cecrud.model.NotaDisponivel;
import com.cecrud.repository.CaixaEletronicoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CeCrudApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class CaixaEletronicoControllerTest {
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private CaixaEletronicoRepository repository;
	
	@SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	@Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		repository.deleteAllInBatch();
	}

	@Test
	public void criar() throws IOException, Exception{
		NotaDisponivel dez = new NotaDisponivel(10,5);
		List<NotaDisponivel>  notas = new ArrayList<>();
		notas.add(dez);
		
		this.mockMvc.perform(post("/caixaeletronico/centro")
                .contentType(contentType)
                .content(this.json(notas)))
                .andExpect(status().isCreated());
	}
	@Test
	public void criarComNomesIguais() throws IOException, Exception{
		NotaDisponivel dez = new NotaDisponivel(10,5);
		List<NotaDisponivel>  notas = new ArrayList<>();
		notas.add(dez);
		
		this.mockMvc.perform(post("/caixaeletronico/centro")
                .contentType(contentType)
                .content(this.json(notas)))
                .andExpect(status().isCreated());
		this.mockMvc.perform(post("/caixaeletronico/centro")
                .contentType(contentType)
                .content(this.json(notas)))
                .andExpect(status().isBadRequest());
	}
	
	@SuppressWarnings("unchecked")
	protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
