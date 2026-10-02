package com.wgabbriel.budgetapi.domain;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Categorias disponíveis para classificar uma transação.")
public enum Category {
  @Schema(description = "Mercado e supermercado")
  GROCERIES,
  @Schema(description = "Farmácia e medicamentos")
  PHARMA,
  @Schema(description = "Automóvel e combustível")
  AUTO,
  @Schema(description = "Outros")
  OTHER
}
