package com.sda.onlineAuction.service;

import com.sda.onlineAuction.dto.ProductDto;
import com.sda.onlineAuction.mapper.ProductMapper;
import com.sda.onlineAuction.model.Product;
import com.sda.onlineAuction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public void add(ProductDto productDto, MultipartFile multipartFile) {
        Product product = productMapper.map(productDto, multipartFile);
        productRepository.save(product);

    }

    public List<ProductDto> getAllProductsDtos(String email) {
        List<Product> products = productRepository.findAll();
        List<ProductDto> result = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = productMapper.map(product, email);
            result.add(productDto);
        }
        return result;
    }

    public List<ProductDto> getAllActiveProductDtos(String email) {
        List<Product> products = productRepository.findAllByEndDateTimeAfter(LocalDateTime.now());
        List<ProductDto> result = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = productMapper.map(product, email);
            result.add(productDto);
        }
        return result;
    }

//    public List<ProductDto> getAllProductsDtosWithStream() {
//        List<Product> productList = productRepository.findAll();
//        productList.stream()
//                .map(productMapper::map)
//                .collect(Collectors.toList());
//    }

    public Optional<ProductDto> getProductById(String productId, String email) {
        Optional<Product> optionalProduct = productRepository.findById(Integer.valueOf(productId));
        if (!optionalProduct.isPresent()) {
            return Optional.empty();
        }
        Product product = optionalProduct.get();
        ProductDto productDto = productMapper.map(product, email);
        return Optional.of(productDto);
    }


    public List<ProductDto> getProductDtosFor(String email) {
        List<Product> products = productRepository.findByWinnerEmail(email);
        List<ProductDto> result = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = productMapper.map(product, email);
            result.add(productDto);
        }
        return result;
    }
}
