package com.wgabbriel.budgetapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.ResponseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class OpenAIChatModelIT {

  @Autowired
  OpenAiApi openAiApi;

  @Test
  void shouldReceiveResponseWhenChatModelIsUsed() {

    var options = OpenAiChatOptions.builder()
        .model("openai/gpt-6-luna")
        .temperature(0.7)
        .responseFormat(ResponseFormat.builder().type(ResponseFormat.Type.TEXT).build())
        .build();

    var chatModel = OpenAiChatModel.builder()
        .openAiApi(openAiApi).defaultOptions(options).build();

    var response = chatModel
        .call("Gere um registro de budeting, com descrição de gastos, gasto, valor em reais e local");

    assertThat(response).isNotNull();
    System.out.println("Response from OpenAI: " + response);

  }
}