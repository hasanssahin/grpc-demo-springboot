package com.hasansahin.product.dto.converter;

import com.hasansahin.product.dto.request.CreateCategoryRequest;
import com.hasansahin.product.dto.response.CategoryResponse;
import com.hasansahin.product.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    public Category convertToEntity(CreateCategoryRequest createCategoryRequest) {
        Category category = new Category();
        category.setName(createCategoryRequest.getName());
        return category;

    }
    public CategoryResponse convertToResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }
}
