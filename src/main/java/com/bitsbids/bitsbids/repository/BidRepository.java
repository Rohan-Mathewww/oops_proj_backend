package com.bitsbids.bitsbids.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitsbids.bitsbids.entity.BidEntity;


@Repository
public interface BidRepository extends JpaRepository<BidEntity,UUID> {
    
}
