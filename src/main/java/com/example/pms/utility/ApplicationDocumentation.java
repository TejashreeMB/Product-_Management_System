package com.example.pms.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class ApplicationDocumentation {
	@Bean
	Contact contact()
	{
		return new Contact().name("Tej")
				.email("tej@gmail.com")
				.url("xyz.in");
	}
	
	@Bean
	Info info() {
		return new Info().title("Product Management System")
				.description("RESTful API with basic CRUD operation.")
				.version("v1").contact(contact());
	}
	
	@Bean
	OpenAPI openApi() {
		return new OpenAPI().info(info());
	}

}
