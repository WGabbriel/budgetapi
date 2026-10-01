package com.wgabbriel.budgetapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Transaction {
  private TransactionID id;
  private String description;
  private Long amount;
  private Category category;

  public Transaction(String description, Long amount, Category category) {
    this.id = new TransactionID();
    this.description = description;
    this.amount = amount;
    this.category = category;
  }
}
