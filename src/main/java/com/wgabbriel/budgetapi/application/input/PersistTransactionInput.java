package com.wgabbriel.budgetapi.application.input;

import org.springframework.ai.tool.annotation.ToolParam;

import com.wgabbriel.budgetapi.domain.Category;

public record PersistTransactionInput(
    @ToolParam(description = "The description of the transaction") String description,
    @ToolParam(description = "The amount of the transaction") Long amount,
    @ToolParam(description = "The category of the transaction") Category category) {
}
