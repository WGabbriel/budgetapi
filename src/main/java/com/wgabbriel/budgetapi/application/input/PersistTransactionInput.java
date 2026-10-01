package com.wgabbriel.budgetapi.application.input;

import com.wgabbriel.budgetapi.domain.Category;

public record PersistTransactionInput(String description, Long amount, Category category) {
}
