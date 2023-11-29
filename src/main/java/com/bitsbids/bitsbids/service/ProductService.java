package com.bitsbids.bitsbids.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
// import java.util.stream.Collectors;
// import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitsbids.bitsbids.entity.ProductEntity;
import com.bitsbids.bitsbids.entity.UserEntity;
import com.bitsbids.bitsbids.repository.ProductRepository;

@Service
public class ProductService{
    @Autowired
    private ProductRepository productRepository;

    public ProductEntity createProduct(ProductEntity product) {
        return productRepository.save(product);
    }
    
    public List<ProductEntity> findProductsSoldByUserId(UserEntity user) {
        return productRepository.findAllBySellerAndProductSoldIsTrue(user);
    }
    public List<ProductEntity> findProductsSellingByUserId(UserEntity user) {
        return productRepository.findAllBySellerAndProductSoldIsTrue(user);
    }
    public List<ProductEntity> findProductsOrderedByUserId(UserEntity user) {
        return productRepository.findAllByHighestBidBidderUserIdAndProductSoldIsTrue(user);
    }
     public List<ProductEntity> findProductsBidByUserId(UserEntity user) {
        return productRepository.findAllByBidsBidderUserIdAndProductSoldIsFalse(user);
    }
  
    public List<ProductEntity> findProductsBySearch(String searchString, String category, String condition) {
        return productRepository.findByTitleAndCategoryAndCondition(searchString, category, condition);
        // has to be of the form title, category, condition
        // findByTitleAndCategoryAndCondition(null,null,null) if you want find all
    }
    public Optional<ProductEntity> findProductById(UUID productId){
        return productRepository.findById(productId);
    }
    public void deleteProduct(UUID productId){
        productRepository.deleteById(productId); //deletes products after bid time expires and noone bids
    }
     // public List<ProductEntity> findProducts(){
    //     //return StreamSupport.stream(productRepository.findAll().spliterator(),false).collect(Collectors.toList());
    //     return productRepository.findAll();
    // }

}
