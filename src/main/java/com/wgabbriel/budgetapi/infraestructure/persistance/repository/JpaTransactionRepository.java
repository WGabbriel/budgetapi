package com.wgabbriel.budgetapi.infraestructure.persistance.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wgabbriel.budgetapi.domain.Category;
import com.wgabbriel.budgetapi.domain.Transaction;
import com.wgabbriel.budgetapi.domain.TransactionRepository;
import com.wgabbriel.budgetapi.infraestructure.persistance.entity.TransactionEntity;

@Repository
public class JpaTransactionRepository implements TransactionRepository {
  private final TransactionEntityRepository transactionEntityRepository;

  public JpaTransactionRepository(TransactionEntityRepository transactionEntityRepository) {
    this.transactionEntityRepository = transactionEntityRepository;
  }

  @Override
  public Transaction save(Transaction transaction) {
    var entity = TransactionEntity.from(transaction);
    return transactionEntityRepository.save(entity).toDomain();
  }

  @Override
  public List<Transaction> findAllByCategory(Category category) {
    return transactionEntityRepository.findAllByCategory(category).stream().map(TransactionEntity::toDomain)
        .toList();
  }

}
