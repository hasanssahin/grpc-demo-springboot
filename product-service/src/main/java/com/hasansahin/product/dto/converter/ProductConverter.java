package com.hasansahin.product.dto.converter;

import com.hasansahin.product.dto.request.CreateProductRequest;
import com.hasansahin.product.dto.response.ProductResponse;
import com.hasansahin.product.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductConverter {
    public Product convertToEntity(CreateProductRequest createProductRequest) {
        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setDescription(createProductRequest.getDescription());
        product.setQuantity(createProductRequest.getQuantity());
        product.setPrice(createProductRequest.getPrice());
        product.setCategory(createProductRequest.getCategory());
        return product;

    }

    public ProductResponse convertToResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setPrice(product.getPrice());
        productResponse.setCategory(product.getCategory());
        return productResponse;
    }

    public List<ProductResponse> convertToResponseList(List<Product> products) {
        return products.stream().map(this::convertToResponse).toList();
    }
}
