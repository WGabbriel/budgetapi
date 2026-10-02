package com.wgabbriel.budgetapi.infraestructure.http;

import java.util.List;

import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.ai.audio.tts.TextToSpeechModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wgabbriel.budgetapi.application.ListTransactionByCategoryUseCase;
import com.wgabbriel.budgetapi.application.PersistTransactionUseCase;
import com.wgabbriel.budgetapi.domain.Category;
import com.wgabbriel.budgetapi.infraestructure.http.request.TransactionRequest;
import com.wgabbriel.budgetapi.infraestructure.http.response.TransactionResponse;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

  private final PersistTransactionUseCase persistTransactionUseCase;
  private final ListTransactionByCategoryUseCase listTransactionByCategoryUseCase;
  private final TranscriptionModel transcriptionModel;
  private final ChatClient chatClient;
  private final TextToSpeechModel textToSpeechModel;

  public TransactionController(
      PersistTransactionUseCase persistTransactionUseCase,
      ListTransactionByCategoryUseCase listTransactionByCategoryUseCase, TranscriptionModel transcriptionModel,
      ChatClient.Builder chatClientBuilder,
      TextToSpeechModel textToSpeechModel,
      @Value("classpath:/prompts/system-message.st") Resource systemPrompt) {

    this.persistTransactionUseCase = persistTransactionUseCase;
    this.listTransactionByCategoryUseCase = listTransactionByCategoryUseCase;
    this.transcriptionModel = transcriptionModel;
    this.textToSpeechModel = textToSpeechModel;
    this.chatClient = chatClientBuilder.defaultSystem(systemPrompt)
        .defaultTools(persistTransactionUseCase, listTransactionByCategoryUseCase).build();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TransactionResponse createTransaction(@RequestBody TransactionRequest request) {
    var transaction = persistTransactionUseCase.execute(request.toInput());
    return TransactionResponse.from(transaction);
  }

  @GetMapping("/{category}")
  public List<TransactionResponse> readTransactions(@PathVariable Category category) {
    return listTransactionByCategoryUseCase.execute(category).stream()
        .map(TransactionResponse::from)
        .toList();
  }

  @PostMapping(value = "/ai", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "audio/mp3")
  public ResponseEntity<Resource> transcribe(@RequestParam("file") MultipartFile file) {
    
    var userMessage = transcriptionModel.transcribe(file.getResource());
    var result = chatClient.prompt().user(userMessage).call().content();
    
    byte[] audio = textToSpeechModel.call(result);
    var resource = new ByteArrayResource(audio);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION,
            ContentDisposition.attachment().filename("file.mp3").build().toString())
        .body(resource);
  }
}
