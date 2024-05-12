package com.hasansahin.product.controller;

import com.hasansahin.product.dto.request.CreateProductRequest;
import com.hasansahin.product.dto.response.ProductResponse;
import com.hasansahin.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody CreateProductRequest createProductRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.addProduct(createProductRequest));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getAllProducts());
    }
}
