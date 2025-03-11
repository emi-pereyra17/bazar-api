package com.bazar.proyectoBazar;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@OpenAPIDefinition(
		servers = @Server(url = "https://bazar-api-production-8d6f.up.railway.app")
)
public class ProyectoBazarApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoBazarApplication.class, args);
	}

	@Configuration
	public static class MyConfiguration {
		@Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**")
							.allowedOrigins("*")
							.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
							.allowedHeaders("*")
							.allowCredentials(false);
				}
			};
		}
	}
}

