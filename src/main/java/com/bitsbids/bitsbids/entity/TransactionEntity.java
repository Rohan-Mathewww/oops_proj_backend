package com.bitsbids.bitsbids.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(generator="UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "transaction_id", columnDefinition = "uuid")
    private UUID transactionId;

    @ManyToOne
    @JoinColumn(name = "paid_from_id")
    private WalletEntity paidFrom;

    @ManyToOne
    @JoinColumn(name = "paid_to_id")
    private WalletEntity paidTo;

    @Column(name = "time_created")
    private Timestamp timeCreated;
    @PrePersist
    public void prePersist() {
    this.timeCreated = new Timestamp(System.currentTimeMillis());
    }

    // Getters and setters
}

