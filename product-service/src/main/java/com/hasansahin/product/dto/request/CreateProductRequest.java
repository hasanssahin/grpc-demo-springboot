package com.hasansahin.product.dto.request;


import com.hasansahin.product.model.Category;
import lombok.Getter;

import java.math.BigDecimal;
@Getter
public class CreateProductRequest {
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    private Category category;
}
