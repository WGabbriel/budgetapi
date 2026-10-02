package com.wgabbriel.budgetapi.application;

public final class MoneyNormalizer {

  private MoneyNormalizer() {
  }

  public static long normalize(Long amount) {
    if (amount == null) {
      return 0L;
    }

    if (amount < 1000L) {
      return amount * 100L;
    }

    return amount;
  }
}
