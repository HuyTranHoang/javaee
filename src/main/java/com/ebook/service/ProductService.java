package com.ebook.service;

import com.ebook.dao.ProductDAO;
import com.ebook.entity.Product;

import java.util.List;

public class ProductService {

    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAllCategories() {
        return productDAO.findAll();
    }

    public Product getProductById(int id) {
        return productDAO.find(id);
    }

    public void insertProduct(Product category) {
        productDAO.create(category);
    }

    public void updateProduct(Product category) {
        productDAO.update(category);
    }

    public void deleteProduct(int id) {
        productDAO.delete(id);
    }
}
