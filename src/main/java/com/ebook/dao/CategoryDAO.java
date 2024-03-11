package com.ebook.dao;

import com.ebook.entity.Category;

public class CategoryDAO extends JpaDAO<Category> {
    public CategoryDAO() {
        super(Category.class);
    }

    public Category findByName(String name) {
        return super.findOneWithHQL("Category.findByName", "name", name);
    }
}
