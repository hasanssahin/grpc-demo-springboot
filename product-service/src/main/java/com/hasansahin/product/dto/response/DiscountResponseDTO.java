package com.hasansahin.product.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DiscountResponseDTO {
    private float newPrice;
    private float oldPrice;
    private String discountCode;
}
