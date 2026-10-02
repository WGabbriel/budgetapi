package com.wgabbriel.budgetapi.application;

import java.util.List;

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

  public List<TransactionOutput> execute(Category category) {
    return transactionRepository.findAllByCategory(category).stream()
        .map(TransactionOutput::from)
        .toList();
  }
}
