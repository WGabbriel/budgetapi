package com.wgabbriel.budgetapi.infraestructure.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class OpenApiConfig {

  @Bean
  OpenAPI budgetApiOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Budget API")
            .version("v0.0.1")
            .description("API para registro e consulta de transações financeiras, "
                + "com suporte a transcrição de áudio e resposta em voz via IA."))
        .addTagsItem(new Tag()
            .name("Transações")
            .description("Operações de criação, listagem e interação por voz com transações."));
  }
}
