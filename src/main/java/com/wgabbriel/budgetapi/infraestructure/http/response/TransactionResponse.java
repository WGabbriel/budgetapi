package com.wgabbriel.budgetapi.infraestructure.http.response;

import com.wgabbriel.budgetapi.application.output.TransactionOutput;

public record TransactionResponse(String id, String description, double amount, String category) {

  public static TransactionResponse from(TransactionOutput output) {

    return new TransactionResponse(
        output.id(),
        output.description(),
        output.value(),
        output.category());
  }
}