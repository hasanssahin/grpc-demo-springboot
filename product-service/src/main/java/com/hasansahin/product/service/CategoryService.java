package com.hasansahin.product.service;

import com.hasansahin.product.dto.converter.CategoryConverter;
import com.hasansahin.product.dto.request.CreateCategoryRequest;
import com.hasansahin.product.dto.response.CategoryResponse;
import com.hasansahin.product.model.Category;
import com.hasansahin.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    public CategoryResponse addCategory(CreateCategoryRequest createCategoryRequest) {
        Category category=categoryConverter.convertToEntity(createCategoryRequest);
        return categoryConverter.convertToResponse(categoryRepository.save(category));
    }

}
