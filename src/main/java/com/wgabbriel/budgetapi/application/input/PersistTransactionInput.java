package com.wgabbriel.budgetapi.application.input;

import org.springframework.ai.tool.annotation.ToolParam;

import com.wgabbriel.budgetapi.domain.Category;

public record PersistTransactionInput(
    @ToolParam(description = "The description of the transaction") String description,
    @ToolParam(description = "The amount of the transaction in cents. If the user says a value like 200, interpret it as R$ 200.00 and convert it to 20000 cents.") Long amount,
    @ToolParam(description = "The category of the transaction") Category category) {
}
