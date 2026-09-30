package com.wgabbriel.budgetapi;

import org.springframework.ai.audio.tts.TextToSpeechModel;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TextToSpeechController {

  private final TextToSpeechModel openAISpeechModel;

  public TextToSpeechController(TextToSpeechModel openAISpeechModel) {
    this.openAISpeechModel = openAISpeechModel;
  }

  @PostMapping(value = "/synthesize", produces = "audio/mp3")
  public ResponseEntity<Resource> synthesize(@RequestBody SynthesizeRequest request) {
    byte[] audio = openAISpeechModel.call(request.text());
    var resource = new ByteArrayResource(audio);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION,
            ContentDisposition.attachment().filename("file.mp3").build().toString())
        .body(resource);

  }

  public record SynthesizeRequest(String text) {
  }
}