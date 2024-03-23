package com.ebook.controller.client;

import com.ebook.entity.Category;
import com.ebook.entity.Product;
import com.ebook.service.CategoryService;
import com.ebook.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Product", value = "/product")
public class ProductServlet extends HttpServlet {


    private ProductService productService;
    private CategoryService categoryService;
    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.cfg.xml");
        this.productService = context.getBean("productService", ProductService.class);
        this.categoryService = context.getBean("categoryService", CategoryService.class);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            listProduct(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String action = pathParts[1];

        switch (action) {
            default:
                listProduct(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List <Product> products;

        String categoryId = request.getParameter("categoryId");

        if (categoryId == null || categoryId.equals("0")) {
            products = productService.getAllProduct();
        } else {
            products = productService.getProductsByCategory(Integer.parseInt(categoryId));
        }

        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/client/product_list.jsp");
        dispatcher.forward(request, response);
    }
}