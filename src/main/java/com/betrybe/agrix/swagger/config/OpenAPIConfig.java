package com.betrybe.agrix.swagger.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
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

    return new OpenAPI().info(info);
  }
}
