package com.bitsbids.bitsbids.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
// import java.util.stream.Collectors;
// import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitsbids.bitsbids.entity.ProductEntity;
import com.bitsbids.bitsbids.repository.ProductRepository;

@Service
public class ProductService{
    @Autowired
    private ProductRepository productRepository;

    public ProductEntity createProduct(ProductEntity product) {
        return productRepository.save(product);
    }
    // public List<ProductEntity> saveProducts(List<ProductEntity> products) {
    //     return productRepository.saveAll(products);
    // }
    // public List<ProductEntity> findProductsById(List<UUID> product_ids) {
    //     return productRepository.findAllById(product_ids);
    // }
    // public List<ProductEntity> findProductsByCondition(String condition) {
    //     return productRepository.findAllByCondition(condition);
    // }
    // public List<ProductEntity> findProductsByCategory(String condition) {
    //     return productRepository.findAllByCategory(condition);
    // }
    //  public List<ProductEntity> findProductsByTitle(String title) {
    //     return productRepository.findAllByTitle(title);
    // }
    public List<ProductEntity> findProductsBySearch(String searchString, String category, String condition) {
        return productRepository.findByTitleAndCategoryAndCondition(searchString, category, condition);
        // has to be of the form title, category, condition
    }
    public List<ProductEntity> findProducts(){
        //return StreamSupport.stream(productRepository.findAll().spliterator(),false).collect(Collectors.toList());
        return productRepository.findAll();
    }
    public Optional<ProductEntity> findProductById(UUID uuid){
        return productRepository.findById(uuid);
    }
    public void deleteProduct(UUID productId){
        productRepository.deleteById(productId);
    }

}
