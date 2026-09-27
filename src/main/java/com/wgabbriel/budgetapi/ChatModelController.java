package com.wgabbriel.budgetapi;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatModelController {

  private final OpenAiChatModel openAiChatModel;

  public ChatModelController(OpenAiChatModel openAiChatModel) {
    this.openAiChatModel = openAiChatModel;
  }

  @GetMapping("/chatmodel")
  String chatModel(String prompt) {
    return this.openAiChatModel.call(prompt);
  }

}