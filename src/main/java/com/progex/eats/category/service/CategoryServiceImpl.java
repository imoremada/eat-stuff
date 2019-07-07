package com.progex.eats.category.service;

import com.progex.eats.category.Category;
import com.progex.eats.category.repo.CategoryRepository;
import com.progex.eats.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepo;


    @Override
    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Optional<Category> getById(int id) {
        return categoryRepo.findById(id);
    }

    @Override
    public void delete(int id) {
        getById(id).ifPresentOrElse(
                category -> categoryRepo.delete(category),
                () -> {
                    log.warn("No category found with id = {}. Delete operation has been failed");
                    throw new EntityNotFoundException("Category cannot be deleted, No such category with id = " + id);
                });
    }

    @Override
    public Page<Category> getPaginated(Pageable pageable) {
        return categoryRepo.findAll(pageable);
    }

}
