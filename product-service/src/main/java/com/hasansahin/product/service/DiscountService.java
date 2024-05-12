package com.hasansahin.product.service;

import com.hasansahin.grpc.DiscountRequest;
import com.hasansahin.grpc.DiscountResponse;
import com.hasansahin.product.dto.response.DiscountResponseDTO;
import com.hasansahin.product.model.Product;
import com.hasansahin.product.service.grpc.DiscountGrpcServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountService {
    private final DiscountGrpcServiceImpl discountGrpcServiceImpl;
    private final ProductService productService;

    public DiscountResponseDTO getDiscount(Long productId, String discountCode) {
        Product product = productService.getById(productId);
        DiscountRequest discountRequest = DiscountRequest.newBuilder()
                .setExternalCategoryId(product.getCategory().getId())
                .setDiscountCode(discountCode)
                .setPrice(product.getPrice().floatValue())
                .build();
        DiscountResponse discountResponse= discountGrpcServiceImpl.getDiscount(discountRequest);
        return DiscountResponseDTO.builder()
                .discountCode(discountCode)
                .newPrice(discountResponse.getNewPrice())
                .oldPrice(discountResponse.getOldPrice())
                .build();
    }
}
