package com.bitsbids.bitsbids.controller;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bitsbids.bitsbids.entity.ProductEntity;
import com.bitsbids.bitsbids.repository.TestDataUtil;
// import com.bitsbids.bitsbids.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class ProductControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestDataUtil testDataUtil;
    // @Autowired
    // private ProductService productService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testThatProductCanBeCreated() throws Exception{
        ProductEntity productEntity = testDataUtil.createTestProduct(UUID.fromString("8f4b83cf-bd76-46fa-80a6-35c79bed2160"));
        String productJson = objectMapper.writeValueAsString(productEntity);
        
        mockMvc.perform(
            MockMvcRequestBuilders.post("/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(productJson)
        ).andExpect(
            MockMvcResultMatchers.status().isCreated()
        );
    }
    @Test
    public void testThatProductCanBeCreatedAndSavesAuthor() throws Exception{
        ProductEntity productEntity = testDataUtil.createTestProduct(UUID.fromString("8f4b83cf-bd76-46fa-80a6-35c79bed2160"));
        String productJson = objectMapper.writeValueAsString(productEntity);
        
        mockMvc.perform(
            MockMvcRequestBuilders.post("/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(productJson)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.basePrice").isNumber()
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.title").value("Cricket Gloves")
        );
    }
    
}
