package com.ebook.dao;

import com.ebook.entity.Product;

public class ProductDAO extends JpaDAO<Product>{
    public ProductDAO() {
        super(Product.class);
    }
}
