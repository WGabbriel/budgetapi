package com.wgabbriel.budgetapi.infraestructure.http.response;

import com.wgabbriel.budgetapi.application.output.TransactionOutput;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representação de uma transação retornada pela API.")
public record TransactionResponse(
    @Schema(description = "Identificador único da transação", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6") String id,
    @Schema(description = "Descrição da transação", example = "Compra no mercado") String description,
    @Schema(description = "Valor da transação", example = "150.50") double amount,
    @Schema(description = "Categoria da transação", example = "GROCERIES") String category) {

  public static TransactionResponse from(TransactionOutput output) {

    return new TransactionResponse(
        output.id(),
        output.description(),
        output.value(),
        output.category());
  }
}