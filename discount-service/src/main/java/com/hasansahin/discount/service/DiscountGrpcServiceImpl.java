package com.hasansahin.discount.service;

import com.hasansahin.discount.model.Category;
import com.hasansahin.discount.model.Discount;
import com.hasansahin.discount.repository.CategoryRepository;
import com.hasansahin.discount.repository.DiscountRepository;
import com.hasansahin.grpc.DiscountRequest;
import com.hasansahin.grpc.DiscountResponse;
import com.hasansahin.grpc.DiscountServiceGrpc;
import com.hasansahin.grpc.Response;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.math.BigDecimal;
import java.util.Optional;

@GrpcService
@RequiredArgsConstructor
public class DiscountGrpcServiceImpl extends DiscountServiceGrpc.DiscountServiceImplBase {
    private final DiscountRepository discountRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void getDiscount(DiscountRequest request, StreamObserver<DiscountResponse> responseObserver) {
        Category category = categoryRepository.findByExternalId(String.valueOf(request.getExternalCategoryId()))
                .orElseThrow(() -> new RuntimeException("Category has not been found by external category id"));

        Optional<Discount> discount = discountRepository.findByDiscountCodeAndCategoryId(request.getDiscountCode(), category.getId());
        if (discount.isPresent()) {
            BigDecimal newPrice = discount.get().getDiscountAmount().subtract(BigDecimal.valueOf(request.getPrice())).multiply(BigDecimal.valueOf(-1));
            responseObserver.onNext(DiscountResponse.newBuilder()
                    .setDiscountCode(request.getDiscountCode())
                    .setOldPrice(request.getPrice())
                    .setNewPrice(newPrice.longValue())
                    .setResponse(Response.newBuilder()
                            .setMessage("Discount has been applied successfully")
                            .setStatus(true)
                            .build())
                    .build());

        } else {
            responseObserver.onNext(DiscountResponse.newBuilder()
                    .setDiscountCode(request.getDiscountCode())
                    .setOldPrice(request.getPrice())
                    .setNewPrice(request.getPrice())
                    .setResponse(Response.newBuilder()
                            .setMessage("Code and category are invalid")
                            .setStatus(false)
                            .build())
                    .build());
        }
        responseObserver.onCompleted();
    }
}