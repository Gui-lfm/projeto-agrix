package com.betrybe.agrix.swagger.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    Contact contact = new Contact();

    contact.name("Guilherme Lucena");
    contact.url("https://github.com/Gui-lfm/projeto-agrix");

    Info info = new Info()
        .title("Projeto Agrix")
        .version("v1.0.0")
        .description("Documentação da API do Projeto Agrix")
        .contact(contact);

    SecurityScheme securityScheme = new SecurityScheme()
        .type(SecurityScheme.Type.HTTP)
        .scheme("Bearer")
        .bearerFormat("JWT")
        .in(In.HEADER)
        .name("Authorization");

    SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

    return new OpenAPI().components(new Components()
        .addSecuritySchemes("bearerAuth", securityScheme))
        .addSecurityItem(securityRequirement)
        .info(info);
  }
}
