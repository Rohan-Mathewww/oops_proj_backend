package com.bitsbids.bitsbids.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitsbids.bitsbids.entity.TransactionEntity;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity,UUID>{
    // the second argument to the JpaRepository is the primary key of the entity
    
}
