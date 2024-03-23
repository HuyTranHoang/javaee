package com.ebook.dao;

import com.ebook.entity.Product;

import java.util.List;

public class ProductDAO extends JpaDAO<Product>{
    public ProductDAO() {
        super(Product.class);
    }

    public List<Product> getByCategory(int categoryId) {
        return super.getByField("category", categoryId);
    }
}
