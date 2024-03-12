package com.ebook.controller.admin;

import com.ebook.entity.Product;
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

@WebServlet(name = "ProductController", value = "/admin/products/*")
public class ProductController extends HttpServlet {

    private ProductService productService;
    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.cfg.xml");
        this.productService = context.getBean("productService", ProductService.class);
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
            case "new":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response, pathParts[2]);
                break;
            default:
                listProduct(request, response);
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = this.productService.getAllCategories();
        request.setAttribute("products", products);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product/product_list.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = new Product();
        request.setAttribute("product", product);
        request.setAttribute("mode", "create");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product/product_form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response, String productIdString) throws ServletException, IOException {
        int productId = Integer.parseInt(productIdString);
        Product existingProduct = this.productService.getProductById(productId);

        if (existingProduct == null) {
            request.getSession().setAttribute("error", "Product with id " + productId + " does not exist!");
            response.sendRedirect(request.getContextPath() + "/admin/products");
            return;
        }

        request.setAttribute("product", existingProduct);
        request.setAttribute("mode", "edit");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product/product_form.jsp");
        dispatcher.forward(request, response);
    }
}
