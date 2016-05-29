package com.cecrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CeCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CeCrudApplication.class, args);
	}
	
	@RequestMapping("/")
	public String teste(){
		return "Hello";
	}
}
