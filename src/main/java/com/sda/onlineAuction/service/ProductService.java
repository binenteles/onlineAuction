package com.sda.onlineAuction.service;

import com.sda.onlineAuction.dto.ProductDto;
import com.sda.onlineAuction.model.Category;
import com.sda.onlineAuction.model.Product;
import com.sda.onlineAuction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void add(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCategory(Category.valueOf(productDto.getCategory()));
        product.setStartingPrice(Integer.valueOf(productDto.getStartBiddingPrice()));
        product.setEndDateTime(LocalDateTime.parse(productDto.getEndDateTime()));
        productRepository.save(product);

    }
}
