package com.bitsbids.bitsbids.repository;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bitsbids.bitsbids.entity.ProductEntity;
import com.bitsbids.bitsbids.entity.UserEntity;
import com.bitsbids.bitsbids.service.UserService;

@Component
public class TestDataUtil {
    @Autowired
    private UserService userService;
    public ProductEntity createTestProduct(UUID sellerId){
        UserEntity retrievedUserEntity = userService.findUserById(sellerId).get();
        Timestamp startTimestamp = Timestamp.from(ZonedDateTime.parse("2023-11-28T08:36:18.933+00:00").toInstant());
        Timestamp endTimestamp = Timestamp.from(ZonedDateTime.parse("2023-11-28T09:36:18.933+00:00").toInstant());
        ProductEntity product = ProductEntity.builder()
                                .seller(retrievedUserEntity)
                                .bidStartTime(startTimestamp)
                                .bidEndTime(endTimestamp)
                                .title("Cricket Gloves")
                                .category("sports")
                                .condition("barely used")
                                .description("SG cricket gloves size 10, with red leather stitching")
                                .basePrice(1600).build();
        return product;
    }
}
