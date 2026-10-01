package com.wgabbriel.budgetapi.infraestructure.persistance.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.wgabbriel.budgetapi.domain.Category;
import com.wgabbriel.budgetapi.infraestructure.persistance.entity.TransactionEntity;

public interface TransactionEntityRepository extends CrudRepository<TransactionEntity, UUID> {

  List<TransactionEntity> findAllByCategory(Category category);
}
