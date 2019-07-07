package com.progex.eats.category.service;

import com.progex.eats.category.Category;
import com.progex.eats.category.repo.CategoryRepository;
import com.progex.eats.exceptions.EntityNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CategoryServiceImplTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @TestConfiguration
    static class CategoryServiceImplTestContextConfiguration {

        @Bean
        public CategoryService categoryService() {

            return new CategoryServiceImpl();
        }
    }

    @Test
    public void shouldSaveCategory() {
        Category category = createMockCategoryWithoutEntities();
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        Category retCategory = categoryService.save(category);
        assertEquals(category, retCategory);
        verify(categoryRepository, times(1)).save(category);
        verifyNoMoreInteractions(categoryRepository);
    }

    @Test
    public void shouldNotSaveCategoryIfCategoryIsNull() {
        assertNull(categoryService.save(null));
    }

    @Test
    public void shouldReturnCategory() {
        Category category = createMockCategoryWithoutEntities();
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(category));
        assertTrue(categoryService.getById(category.getId()).isPresent());
        verify(categoryRepository, times(1)).findById(anyInt());
        verifyNoMoreInteractions(categoryRepository);
    }

    @Test
    public void shouldReturnEmptyCategoryIfNotPresented() {
        assertTrue(categoryService.getById(2).isEmpty());
    }

    @Test
    public void shouldDeleteCategory() {
        Category category = createMockCategoryWithoutEntities();
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        categoryService.delete(category.getId());
        verify(categoryRepository, times(1)).findById(category.getId());
        verify(categoryRepository, times(1)).delete(category);
        verifyNoMoreInteractions(categoryRepository);
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFoundExceptionWhenDeletingCategory() {
        Category category = createMockCategoryWithoutEntities();
        categoryService.delete(category.getId());
    }

    @Test
    public void shouldReturnListOfCategories() {
        Category cat1 = createMockCategoryWithoutEntities();
        Category cat2 = createMockCategoryWithoutEntities();
        cat2.setId(2);
        List<Category> categoryList = Arrays.asList(cat1, cat2);

        int pageNumber = 0;
        int pageSize = 2;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Category> categoryPage = new PageImpl<>(categoryList);
        when(categoryRepository.findAll(pageable)).thenReturn(categoryPage);
        Page<Category> categories = categoryService.getPaginated(pageable);

        assertEquals(categories.getNumberOfElements(), categoryList.size());
    }

    private Category createMockCategoryWithoutEntities() {
        Category category = new Category();
        category.setId(1);
        category.setName("CategoryName");
        return category;
    }
}
