package com.wgabbriel.budgetapi.infraestructure.http.request;

import com.wgabbriel.budgetapi.application.input.PersistTransactionInput;
import com.wgabbriel.budgetapi.domain.Category;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados necessários para registrar uma nova transação.")
public record TransactionRequest(
    @Schema(description = "Descrição da transação", example = "Compra no mercado") String description,
    @Schema(description = "Valor da transação em centavos. Exemplo: 200 representa R$ 200,00 e deve ser salvo como 20000", example = "20000") long amount,
    @Schema(description = "Categoria da transação", example = "GROCERIES") Category category) {

  public PersistTransactionInput toInput() {
    return new PersistTransactionInput(description, amount, category);
  }

}
