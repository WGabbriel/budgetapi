package com.wgabbriel.budgetapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class ToolCallingIT {
  static class MathTool {

    @Tool(description = "Soma dois números inteiros, a e b")
    public String sum(int a, int b) {
      return String.valueOf(a + b);
    }

    @Tool(description = "Subtrai dois números inteiros, a e b")
    public String subtract(int a, int b) {
      return String.valueOf(a - b);
    }

  }

  @Autowired
  OpenAiChatModel openAiChatModel;

  @Test
  void shouldExecuteSumWhenPrompted() {

    var chatClient = ChatClient.builder(openAiChatModel).defaultSystem("Você é um matemático").defaultTools(new MathTool()).build();

    var response = chatClient
        .prompt(
            "Some 10 mais 20. Depois subtraia 30 do resultado anterior. Exiba apenas o resultado final sem explicações")
        .call().content();

    assertThat(response).contains("0");
    System.out.println("Response from OpenAI: " + response);

  }
}