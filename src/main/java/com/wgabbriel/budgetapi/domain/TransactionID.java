package com.wgabbriel.budgetapi.domain;

import java.util.UUID;

public record TransactionID(UUID uuid) {
  public TransactionID() {
    this(UUID.randomUUID());
  }
}
