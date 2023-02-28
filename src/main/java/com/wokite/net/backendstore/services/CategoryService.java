package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.Category;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category saveCategory(Category category);

    Category updateCategory(Long catId, Category category);

    Optional<Category> findCategoryById(Long catId);

    Category findByDesignation(String designation);

    List<Category> findAllCategories();

    List<Category> findAllCategoriesOrderDesc();

    void deleteCategory(Long catId);
}
