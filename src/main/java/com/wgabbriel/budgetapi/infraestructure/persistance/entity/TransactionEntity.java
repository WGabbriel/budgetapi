package com.wgabbriel.budgetapi.infraestructure.persistance.entity;

import java.util.UUID;

import com.wgabbriel.budgetapi.domain.Category;
import com.wgabbriel.budgetapi.domain.Transaction;
import com.wgabbriel.budgetapi.domain.TransactionID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
  @Id
  private UUID id;

  private String description;

  private long amount;

  @Enumerated(EnumType.STRING)
  private Category category;

  public static TransactionEntity from(Transaction transaction) {
    return new TransactionEntity(
        transaction.getId().uuid(),
        transaction.getDescription(),
        transaction.getAmount(),
        transaction.getCategory());
  }

  public Transaction toDomain() {
    return new Transaction(
        new TransactionID(this.id),
        this.description,
        this.amount,
        this.category);
  }
}
