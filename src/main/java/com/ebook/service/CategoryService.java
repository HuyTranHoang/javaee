package com.ebook.service;

import com.ebook.dao.CategoryDAO;
import com.ebook.entity.Category;

import java.util.List;

public class CategoryService {

    private final CategoryDAO categoryDAO;

    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }

    public Category getCategoryById(int id) {
        return categoryDAO.find(id);
    }

    public Category getCategoryByName(String name) {
        return categoryDAO.findByName(name);
    }

    public void insertCategory(Category category) {
        Category existingCategory = categoryDAO.findByName(category.getName());
        if (existingCategory != null) {
            throw new IllegalArgumentException("Category already exists");
        }

        categoryDAO.create(category);
    }

    public void updateCategory(Category category) {
        Category existingCategory = categoryDAO.findByName(category.getName());
        if (existingCategory != null && existingCategory.getCategoryId() != category.getCategoryId()) {
            throw new IllegalArgumentException("Category already exists");
        }

        categoryDAO.update(category);
    }

    public void deleteCategory(int id) {
        categoryDAO.delete(id);
    }
}
