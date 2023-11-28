package com.bitsbids.bitsbids.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "wishlist")
public class WishlistEntity {
    @Id
    @GeneratedValue(generator="UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "wishlist_id", columnDefinition = "uuid")
    private UUID wishlistId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "time_created")
    private Timestamp timeCreated;
    @PrePersist
    public void prePersist() {
    this.timeCreated = new Timestamp(System.currentTimeMillis());
    }

    // Getters and setters
}
