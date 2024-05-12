package com.hasansahin.product.service;

import com.hasansahin.product.dto.converter.ProductConverter;
import com.hasansahin.product.dto.request.CreateProductRequest;
import com.hasansahin.product.dto.response.ProductResponse;
import com.hasansahin.product.model.Product;
import com.hasansahin.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public ProductResponse addProduct(CreateProductRequest createProductRequest) {
        Product product=productConverter.convertToEntity(createProductRequest);
        return productConverter.convertToResponse(productRepository.save(product));
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productConverter.convertToResponseList(products);
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
