package com.bitsbids.bitsbids.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bitsbids.bitsbids.entity.ProductEntity;
import com.bitsbids.bitsbids.entity.dto.ProductDto;
import com.bitsbids.bitsbids.mappers.Mapper;

@Component
public class ProductMapper implements Mapper<ProductEntity, ProductDto>{
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductEntity mapFrom(ProductDto productDto){
        return modelMapper.map(productDto, ProductEntity.class);
    }

    @Override
    public ProductDto mapTo(ProductEntity productEntity){
        return modelMapper.map(productEntity, ProductDto.class);
    }
} 
