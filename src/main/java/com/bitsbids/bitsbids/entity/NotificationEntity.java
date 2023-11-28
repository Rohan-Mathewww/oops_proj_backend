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
@Table(name = "notifications")
public class NotificationEntity {
    @Id
    @GeneratedValue(generator="UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "notification_id", columnDefinition = "uuid")
    private UUID notificationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "time_created")
    private Timestamp timeCreated;

    // Getters and setters
}
