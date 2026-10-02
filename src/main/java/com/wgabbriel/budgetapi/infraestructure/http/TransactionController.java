package com.wgabbriel.budgetapi.infraestructure.http;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

  public TransactionController(PersistTransactionUseCase persistTransactionUseCase,
      ListTransactionByCategoryUseCase listTransactionByCategoryUseCase) {
    this.persistTransactionUseCase = persistTransactionUseCase;
    this.listTransactionByCategoryUseCase = listTransactionByCategoryUseCase;
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

}
