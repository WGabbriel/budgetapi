package com.wgabbriel.budgetapi.application;

import org.springframework.stereotype.Service;

import com.wgabbriel.budgetapi.application.input.PersistTransactionInput;
import com.wgabbriel.budgetapi.application.output.TransactionOutput;
import com.wgabbriel.budgetapi.domain.Transaction;
import com.wgabbriel.budgetapi.domain.TransactionRepository;

@Service
public class PersistTransactionUseCase {
  private final TransactionRepository transactionRepository;

  public PersistTransactionUseCase(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public TransactionOutput execute(PersistTransactionInput input) {
    var transaction = transactionRepository
        .save(new Transaction(input.description(), input.amount(), input.category()));
    return TransactionOutput.from(transaction);
  }
}
