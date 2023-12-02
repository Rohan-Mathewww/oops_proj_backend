package com.bitsbids.bitsbids.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bitsbids.bitsbids.entity.ProductEntity;
//import com.bitsbids.bitsbids.mappers.impl.ProductMapper;
import com.bitsbids.bitsbids.service.ProductService;

@RestController
@RequestMapping(path="/products")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;
   
    // @GetMapping
    // public ResponseEntity<?> getProducts(){
    //     List<ProductEntity> products = productService.findProducts();
    //     return new ResponseEntity<>(products,HttpStatus.OK);// returns a response entity with products and http status code ok
    // }

    // @GetMapping("/searchTitles")
    // public ResponseEntity<List<ProductEntity>> getProductsByTitle(@RequestParam String title) {
    // List<ProductEntity> productsByTitle = productService.findProductsByTitle(title);
    // return new ResponseEntity<>(productsByTitle, HttpStatus.OK);
    // }
    // @GetMapping("/searchCategories")
    // public ResponseEntity<List<ProductEntity>> getProductsByCategory(@RequestParam String category) {
    // List<ProductEntity> productsByCategory = productService.findProductsByCategory(category);
    // return new ResponseEntity<>(productsByCategory, HttpStatus.OK);
    // }

    @GetMapping("/search")
    public ResponseEntity<List<ProductEntity>> searchProducts(
            @RequestParam(required = false) String searchString,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String condition) {
        List<ProductEntity> products = productService.findProductsBySearch(searchString, category, condition);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping //works
    public ResponseEntity<?> createProduct(@RequestBody ProductEntity productEntity) // find what ResponseEntity does
    {
        // String productImageUrlAsString = productDto.getProductImageUrl();
        // List<String> productImageUrls = Arrays.asList(productImageUrlAsString.split(",")); //MAY GIVE AN ERROR IM ASSUMING JSONIGNORE DOES SOMETHING
        
        // ProductEntity productEntity = productMapper.mapFrom(productDto);
        // productEntity.setProductImageUrl(productImageUrls);
        return new ResponseEntity<>(productService.createProduct(productEntity),HttpStatus.CREATED);// created used to indicate successful creation
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") UUID productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
