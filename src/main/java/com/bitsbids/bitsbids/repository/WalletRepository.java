package com.bitsbids.bitsbids.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bitsbids.bitsbids.entity.WalletEntity;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity,UUID>{
    // the second argument to the JpaRepository is the primary key of the entity

}