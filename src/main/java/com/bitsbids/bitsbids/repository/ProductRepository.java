package com.bitsbids.bitsbids.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bitsbids.bitsbids.entity.ProductEntity;
import com.bitsbids.bitsbids.entity.UserEntity;

// the repository is a specialised version of @Component,used to indicate that it fullfils the role of a repo
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,UUID>{
    // the second argument to the JpaRepository is the primary key of the entity
    // List<ProductEntity> findAllByCondition(String condition);
    // List<ProductEntity> findAllByTitle(String title);
    // List<ProductEntity> findAllByCategory(String category);
    
    //Only returns ones where the transaction id is null
    @Query("SELECT p FROM ProductEntity p WHERE " +
    "LOWER(p.title) LIKE CONCAT('%', COALESCE(LOWER(:title), ''), '%') AND " +
    "LOWER(p.category) LIKE CONCAT('%', COALESCE(LOWER(:category), ''), '%') AND " +
    "LOWER(p.condition) LIKE CONCAT('%', COALESCE(LOWER(:condition), ''), '%') AND " +
    "p.transaction IS NULL")
    List<ProductEntity> findByTitleAndCategoryAndCondition(
     @Param("title") String title,
     @Param("category") String category,
     @Param("condition") String conditions);

    @Query("SELECT p FROM ProductEntity p WHERE p.seller = :seller AND p.productSold = false")
    List<ProductEntity> findAllBySellerAndProductSoldIsFalse(@Param("seller") UserEntity seller);
    
    @Query("SELECT p FROM ProductEntity p WHERE p.seller = :seller AND p.productSold = true")
    List<ProductEntity> findAllBySellerAndProductSoldIsTrue(@Param("seller") UserEntity seller);
    
    @Query("SELECT p FROM ProductEntity p JOIN p.highestBid b WHERE b.bidder = :user AND p.productSold = true")
    List<ProductEntity> findAllByHighestBidBidderUserIdAndProductSoldIsTrue(@Param("user") UserEntity user);
    
    @Query("SELECT b FROM BidEntity b JOIN b.bidder u WHERE u = :user AND b.product.productSold = false")
    List<ProductEntity> findAllByBidsBidderUserIdAndProductSoldIsFalse(@Param("user") UserEntity user);
}


// does the queries