package com.intercorpretail.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private static final Contact contact = new Contact("Andres Lizarraga", "https://github.com/AndresLizarraga", "andresalv7@gmail.com");

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.intercorpretail.challenge.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(
						apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Crea Clientes API")
				.description("API que representa la solución del desafio entregado por Intercorp Retail. <br>"
						+ " El endpoint '/creaCliente' crea y persiste un objeto 'cliente' en una base de datos. <br>"
						+ " El endpoint '/kpideclientes' incluye funcionalidad para calcular el promedio de edad de los clientes, así como la desviación estandar de las edades de todos los clientes. <br>"
						+ " El endpoint 'listclientes' arroja una lista de todos los clientes persistidos, mostrando su fecha probable de muerte.")
				.contact(contact)
				.version("1.0").build();
	}
	
}
