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

    public void insertProduct(Product product) {
        productDAO.create(product);
    }

    public void updateProduct(Product product) {
        Product existingProduct = productDAO.find(product.getProductId());

        if (existingProduct == null) {
            throw new IllegalArgumentException("Product with id " + product.getProductId() + " does not exist!");
        }

        productDAO.update(product);
    }

    public void deleteProduct(int id) {
        productDAO.delete(id);
    }
}
