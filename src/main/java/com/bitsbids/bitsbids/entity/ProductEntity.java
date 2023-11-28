package com.bitsbids.bitsbids.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(generator="UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "product_id", columnDefinition = "uuid")
    private UUID productId;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private UserEntity seller;

    @Column(name = "bid_start_time")
    private Timestamp bidStartTime;
 
    @Column(name = "bid_end_time")
    private Timestamp bidEndTime;

    @Column(name = "title")
    private String title;

    @Column(name = "condition")
    private String condition;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "product_image_url")
    private List<String> productImageUrl;

    @Column(name = "base_price")
    private int basePrice;

    @Column(name = "time_created")
    private Timestamp timeCreated;

    @PrePersist
    public void prePersist() {
    this.timeCreated = new Timestamp(System.currentTimeMillis());
    }

    // @Column(name = "total_bids")
    // private int totalBids;

    // @Column(name = "visit_count")
    // private int visitCount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id")
    private TransactionEntity transaction;

    @ManyToOne // should this have  cascade?
    @JoinColumn(name = "highest_bid_id")
    private BidEntity highestBid;


    // Getters and setters
}

