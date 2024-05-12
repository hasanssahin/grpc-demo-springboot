package com.hasansahin.product.service.grpc;

import com.hasansahin.grpc.DiscountRequest;
import com.hasansahin.grpc.DiscountResponse;
import com.hasansahin.grpc.DiscountServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DiscountGrpcServiceImpl implements DiscountGrpcService {
    private final ManagedChannel managedChannel;

    public DiscountGrpcServiceImpl(@Value("${discount.grpc.host}") String grpcHost,
                                   @Value("${discount.grpc.port}") int grpcPort) {
        managedChannel = ManagedChannelBuilder.forAddress(grpcHost, grpcPort)
                .usePlaintext()
                .build();
    }

    @Override
    public DiscountResponse getDiscount(DiscountRequest discountRequest) {
        DiscountServiceGrpc.DiscountServiceBlockingStub discountServiceBlockingStub =
                DiscountServiceGrpc.newBlockingStub(managedChannel);
        return discountServiceBlockingStub.getDiscount(discountRequest);
    }
}
