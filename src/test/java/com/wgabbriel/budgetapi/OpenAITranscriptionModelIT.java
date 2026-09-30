package com.wgabbriel.budgetapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class OpenAITranscriptionModelIT {

  @Autowired
  private OpenAiAudioTranscriptionModel openAITranscriptionModel;

  @ParameterizedTest
  @CsvSource({
      "recording-1.m4a, 80",
      "recording-2.m4a, 40",
      "recording-3.m4a, 120",
      "recording-4.m4a, 90",
      "recording-5.m4a, 200",
      "recording-6.m4a, 60",
  })
  void shouldContainExpectedKeywordsWhenAudioFilesAreProcessed(String fileName, String expectedKeyword) {

    var recording = new ClassPathResource("audio/" + fileName);
    var response = openAITranscriptionModel.call(recording);

    assertThat(response).contains(expectedKeyword);
    System.out.println(response);

  }
}