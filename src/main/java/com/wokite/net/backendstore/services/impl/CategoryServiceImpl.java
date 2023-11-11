package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Category;
import com.wokite.net.backendstore.repository.CategoryRepository;
import com.wokite.net.backendstore.services.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category saveCategory(Category category) {
        Category checkCategorie = categoryRepository.findByCode(category.getCode());
        if (checkCategorie != null) {
            throw new IllegalArgumentException("This Category already exist");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long catId, Category category) {
        if (!categoryRepository.existsById(catId)) {
            throw new ResourceNotFoundException("Category not found");
        }
        Optional<Category> optionalCategory = categoryRepository.findById(catId);
        if (!optionalCategory.isPresent()) {
            throw new ResourceNotFoundException("Category that not found");
        }
        Category categoryResult = optionalCategory.get();
        categoryResult.setCode(category.getCode());
        categoryResult.setDesignation(category.getDesignation());

        return categoryRepository.save(categoryResult);
    }

    @Override
    public Optional<Category> findCategoryById(Long catId) {
        if (!categoryRepository.existsById(catId)) {
            throw new ResourceNotFoundException("Category that id is" + catId + "not found");
        }
        return categoryRepository.findById(catId);
    }

    @Override
    public Category findByDesignation(String designation) {
        return categoryRepository.findByDesignation(designation);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAllCategoriesOrderDesc() {
        return categoryRepository.findByOrderByIdDesc();
    }

    @Override
    public void deleteCategory(Long catId) {
        if (!categoryRepository.existsById(catId)) {
            throw new ResourceNotFoundException("Category not found");
        }
        Category categoryResult = categoryRepository.findById(catId).get();
        categoryResult.setActif(false);
        categoryRepository.save(categoryResult);
    }
}
