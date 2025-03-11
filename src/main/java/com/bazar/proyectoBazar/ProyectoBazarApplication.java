package com.bazar.proyectoBazar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ProyectoBazarApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoBazarApplication.class, args);
	}

	@Configuration
	public static class Myconfiguration {
		@Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**")
							.allowedOrigins("*") // <-- ESTO FALTABA
							.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
							.allowedHeaders("*"); // <-- también recomendable
				}
			};
		}
	}

}
