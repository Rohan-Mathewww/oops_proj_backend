package com.bitsbids.bitsbids.entity;


import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(generator="UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private UUID userId;
    
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "profile_image_url", columnDefinition = "text")
    private String profileImageUrl;

    //@JsonIdentityReference(alwaysAsId = true)
    //the wallet entity can have this to just send the id instead of the whole object.
    @OneToOne
    @JoinColumn(name = "wallet_id") // Name of the column in the users table that holds the wallet_id
    private WalletEntity walletId;

    @Column(name = "time_created")
    private Timestamp timeCreated;

    @PrePersist
    public void prePersist() {
    this.timeCreated = new Timestamp(System.currentTimeMillis());
    }

    // Constructors, getters, setters, etc.
}
