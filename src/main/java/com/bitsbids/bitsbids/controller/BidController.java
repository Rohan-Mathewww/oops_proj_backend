package com.bitsbids.bitsbids.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitsbids.bitsbids.entity.BidEntity;
import com.bitsbids.bitsbids.entity.ProductEntity;
import com.bitsbids.bitsbids.service.BidService;
import com.bitsbids.bitsbids.service.ProductService;

@RestController
@RequestMapping(path="/bids")
@CrossOrigin
public class BidController {
    @Autowired
    private BidService bidService;
    @Autowired
    private ProductService productService;
   
    // @PostMapping("/{productId}")
    // public ResponseEntity<?> createBid(@PathVariable("productId") UUID productId, @RequestBody BidEntity bidEntity) // find what ResponseEntity does
    // {
    //     Optional<ProductEntity> existingProductEntity = productService.findProductById(productId);
    //     if (existingProductEntity.isPresent()) {
    //         ProductEntity retrievedProductEntity = existingProductEntity.get(); // get throws an error.
    //         if (retrievedProductEntity.getHighestBid().getBidAmount()<bidEntity.getBidAmount()){
    //             bidService.createProduct(bidEntity);
    //             retrievedProductEntity.setHighestBid(bidEntity);
    //             return new ResponseEntity<>(bidEntity,HttpStatus.CREATED);
    //         }
    //         return new ResponseEntity<>("Bid not high enough",HttpStatus.BAD_REQUEST); // because the bidEntity doesnt meet the criteria
    //     }
       
    //     else {
    //         return new ResponseEntity<>("Product Id does not exist",HttpStatus.NOT_FOUND);
    //     }
    // }
    @PostMapping("/{productId}")
    public ResponseEntity<?> createBid(@PathVariable("productId") UUID productId, @RequestBody BidEntity bidEntity) // find what ResponseEntity does
    {
        Optional<ProductEntity> existingProductEntity = productService.findProductById(productId);
        if (existingProductEntity.isPresent()) {
            return new ResponseEntity<>(bidEntity,HttpStatus.CREATED);
        } 
       
        else {
            return new ResponseEntity<>("Product Id does not exist",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{bidId}")
    public ResponseEntity<?> getBid(@PathVariable("bidId") UUID bidId){
        Optional<BidEntity> existingBidEntity = bidService.getBid(bidId);
        if (existingBidEntity.isPresent()){
            BidEntity retrievedBidEntity = existingBidEntity.get();
            return new ResponseEntity<>(retrievedBidEntity,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("No bid with that id",HttpStatus.NOT_FOUND);
        }
    }

}
