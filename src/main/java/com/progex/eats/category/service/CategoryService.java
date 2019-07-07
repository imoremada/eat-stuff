package com.progex.eats.category.service;

import com.progex.eats.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {
    Category save(Category category);

    Optional<Category> getById(int id);

    void delete(int id);

    Page<Category> getPaginated(Pageable pageable);
}
