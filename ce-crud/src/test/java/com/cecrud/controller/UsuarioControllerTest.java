package com.cecrud.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

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
import com.cecrud.model.Usuario;
import com.cecrud.repository.UsuarioRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CeCrudApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class UsuarioControllerTest {
	
	protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	protected MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext webApplicationContext;
	
	@SuppressWarnings("rawtypes")
	protected HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private Usuario usuario;
	
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
		usuario = new Usuario("Usuario Teste", 50);
		usuarioRepository.deleteAllInBatch();
	}
	
	@SuppressWarnings("unchecked")
	protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
	
	@Test
	public void salvarUsuarioESaldo() throws IOException, Exception{
		this.mockMvc.perform(post("/usuarios")
	                .contentType(contentType)
	                .content(this.json(usuario)))
	                .andExpect(status().isCreated());
	}
	
	@Test
	public void salvarUsuarioRepetido() throws IOException, Exception{
		usuarioRepository.save(usuario);
		this.mockMvc.perform(post("/usuarios")
	                .contentType(contentType)
	                .content(this.json(usuario)))
	                .andExpect(status().isBadRequest());
	}

	@Test
	public void recuperarUsuarios() throws Exception {
		usuarioRepository.save(usuario);
		mockMvc.perform(get("/usuarios"))
		         .andExpect(status().isOk())
		         .andExpect(content().contentType(contentType))
		         .andExpect(jsonPath("$[0].saldo").value(usuario.getSaldo()))
		         .andExpect(jsonPath("$[0].nome").value(usuario.getNome()));
	}
	
	@Test
	public void recuperarUsuarioPorId() throws Exception {
		usuarioRepository.save(usuario);
		mockMvc.perform(get("/usuarios/" + usuario.getId()))
		         .andExpect(status().isOk())
		         .andExpect(content().contentType(contentType))
		         .andExpect(jsonPath("$.saldo").value(usuario.getSaldo()))
		         .andExpect(jsonPath("$.nome").value(usuario.getNome()));
	}
	
}
