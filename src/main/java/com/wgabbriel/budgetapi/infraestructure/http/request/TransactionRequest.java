package com.wgabbriel.budgetapi.infraestructure.http.request;

import com.wgabbriel.budgetapi.application.input.PersistTransactionInput;
import com.wgabbriel.budgetapi.domain.Category;

public record TransactionRequest(String description, long amount, Category category) {

  public PersistTransactionInput toInput() {
    return new PersistTransactionInput(description, amount, category);
  }

}
