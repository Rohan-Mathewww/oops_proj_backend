package com.bitsbids.bitsbids.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "bids")
public class BidEntity {
    @Id
    @GeneratedValue(generator="UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "bid_id", columnDefinition = "uuid")
    private UUID bidId;

    @Column(name = "bid_amount")
    private int bidAmount;

    @ManyToOne
    @JoinColumn(name = "bidder")
    private UserEntity bidder;

    @ManyToOne
    @JoinColumn(name = "product")
    private ProductEntity product;

    @Column(name = "time_bid_made")
    private Timestamp timeBidMade;

    // Getters and setters
}

