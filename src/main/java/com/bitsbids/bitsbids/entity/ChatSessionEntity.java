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
@Table(name = "chat_sessions")
public class ChatSessionEntity {
    @Id
    @GeneratedValue(generator="UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "chat_id", columnDefinition = "uuid")
    private UUID chatId;

    @ManyToOne
    @JoinColumn(name = "product")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "buyer")
    private UserEntity buyer;

    @GeneratedValue(generator="UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "buyer_anon")
    private UUID buyerAnon;

    @Column(name = "time_created")
    private Timestamp timeCreated;


    // Getters and setters
}

