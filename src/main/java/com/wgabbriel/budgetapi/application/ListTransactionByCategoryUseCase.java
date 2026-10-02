package com.wgabbriel.budgetapi.application;

import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import com.wgabbriel.budgetapi.application.output.TransactionOutput;
import com.wgabbriel.budgetapi.domain.Category;
import com.wgabbriel.budgetapi.domain.TransactionRepository;

@Service
public class ListTransactionByCategoryUseCase {

  private final TransactionRepository transactionRepository;

  public ListTransactionByCategoryUseCase(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  @Tool(name = "list_transactions_by_category", description = "List transactions by category from the database")
  public List<TransactionOutput> execute(
      @ToolParam(description = "The category of the transactions to list") Category category) {

    return transactionRepository.findAllByCategory(category).stream()
        .map(TransactionOutput::from)
        .toList();
  }
}
