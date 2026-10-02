package com.wgabbriel.budgetapi;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "API_KEY", matches = ".+")
public class OpenAISpeechModelIT {

  @Autowired
  private OpenAiAudioSpeechModel openAISpeechModel;

  @Test
  void shouldProduceAudioWhenTextIsProvided() throws IOException {
    var response = openAISpeechModel.call("O valor total do serviço ficou em 80 reais. Posso confirmar o pagamento?");

    assertThat(response).isNotNull();
    assertThat(response).hasSizeGreaterThan(1024);

    var tempFile = Files.createTempFile("AUDIO_", ".mp3");
    Files.write(tempFile, response);

    System.out.println("Audio file created at: " + tempFile.toAbsolutePath());
  }
}
