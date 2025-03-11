package com.bazar.proyectoBazar.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Clientes, Ventas y Productos")
                        .version("1.0")
                        .description("Documentación de la API para un Bazar. Permite la gestión de Clientes, Ventas y Productos en la plataforma.")
                        .contact(new Contact()
                                .name("Emiliano Pereyra")
                                .email("emiacebal2012@hotmail.com")
                                .url("https://github.com/emi-pereyra17")
                        )
                );
    }
}
