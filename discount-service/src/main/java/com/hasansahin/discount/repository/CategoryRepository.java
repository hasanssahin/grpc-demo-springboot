package com.hasansahin.discount.repository;

import com.hasansahin.discount.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByExternalId(String externalId);
}