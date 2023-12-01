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
@Table(name = "chat_messages")
public class ChatMessageEntity {
    @Id
    @GeneratedValue(generator="UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "message_id", columnDefinition = "uuid")
    private UUID messageId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_id")
    private ChatSessionEntity chatSession;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserEntity sender;

    @Column(name = "message_text")
    private String messageText;

    @Column(name = "time_created")
    private Timestamp timeCreated;
    

    // Getters and setters
}

