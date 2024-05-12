package com.hasansahin.product.dto.response;

import com.hasansahin.product.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ProductResponse {
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    private Category category;
}
