package com.wgabbriel.budgetapi.application;

import org.springframework.ai.tool.annotation.Tool;
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

  @Tool(name = "persist_transaction", description = "Persist a transaction in the database")
  public TransactionOutput execute(PersistTransactionInput input) {
    var normalizedAmount = MoneyNormalizer.normalize(input.amount());
    var transaction = transactionRepository
        .save(new Transaction(input.description(), normalizedAmount, input.category()));
    return TransactionOutput.from(transaction);
  }
}
