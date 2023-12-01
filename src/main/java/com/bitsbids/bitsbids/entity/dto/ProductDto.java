package com.bitsbids.bitsbids.entity.dto;

import java.sql.Timestamp;
import java.util.UUID;

import com.bitsbids.bitsbids.entity.BidEntity;
import com.bitsbids.bitsbids.entity.TransactionEntity;
import com.bitsbids.bitsbids.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {
    private UUID productId;

    private UserEntity seller;

    private Timestamp bidStartTime;
 
    private Timestamp bidEndTime;

    private String title;

    private String condition;

    private String category;

    private String description;

    private String productImageUrl; // will be List<String> in the ProductEntity

    private int basePrice;

    private Timestamp timeCreated;

    private boolean productSold;

    private TransactionEntity transaction;

    private BidEntity highestBid;

}
