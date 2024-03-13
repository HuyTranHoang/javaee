package com.ebook.controller.admin;

import com.ebook.entity.Category;
import com.ebook.entity.Product;
import com.ebook.service.CategoryService;
import com.ebook.service.ProductService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@MultipartConfig
@WebServlet(name = "ProductController", value = "/admin/products/*")
public class ProductController extends HttpServlet {

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
            case "new":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response, pathParts[2]);
                break;
            case "image":
                getImage(request, response);
                break;
            default:
                listProduct(request, response);
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getPathInfo() != null ? request.getPathInfo() : "/invalid-action";

        switch (action) {
            case "/insert":
                insertProduct(request, response);
                break;
            case "/delete":
                deleteProduct(request, response);
                break;
            case "/update":
                updateProduct(request, response);
                break;
            default:
                request.getSession().setAttribute("error", "Invalid action!");
                response.sendRedirect(request.getContextPath() + "/admin/categories");
                break;
        }
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

        List<Category> categories = this.categoryService.getAllCategories();
        request.setAttribute("categories", categories);

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

        List<Category> categories = this.categoryService.getAllCategories();
        request.setAttribute("categories", categories);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/product/product_form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Create a custom LocalDate converter
        Converter localDateConverter = getLocalDateConverter();

        // Register the custom LocalDate converter
        ConvertUtils.register(localDateConverter, LocalDate.class);

        Product product = new Product();
        try {
            BeanUtils.populate(product, request.getParameterMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String categoryIdString = request.getParameter("categoryId");
        if (categoryIdString != null) {
            int categoryId = Integer.parseInt(categoryIdString);
            Category category = this.categoryService.getCategoryById(categoryId);
            product.setCategory(category);
        }

        // Get image from request
        Part imagePart;
        try {
            imagePart = request.getPart("image");
            if (imagePart != null) {
                InputStream inputStream = imagePart.getInputStream();
                byte[] imageBytes = new byte[(int) imagePart.getSize()];
                inputStream.read(imageBytes);
                product.setImage(imageBytes);
            }
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

        this.productService.insertProduct(product);

        request.getSession().setAttribute("message", "New product has been added successfully!");

        response.sendRedirect(request.getContextPath() + "/admin/products");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("deleteProductId"));

        this.productService.deleteProduct(id);

        request.getSession().setAttribute("message", "Product has been deleted successfully!");

        response.sendRedirect(request.getContextPath() + "/admin/products");
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Create a custom LocalDate converter
        Converter localDateConverter = getLocalDateConverter();

        // Register the custom LocalDate converter
        ConvertUtils.register(localDateConverter, LocalDate.class);

        Product product = new Product();
        try {
            BeanUtils.populate(product, request.getParameterMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String categoryIdString = request.getParameter("categoryId");
        if (categoryIdString != null) {
            int categoryId = Integer.parseInt(categoryIdString);
            Category category = this.categoryService.getCategoryById(categoryId);
            product.setCategory(category);
        }

        // Get image from request
        Part imagePart;
        try {
            imagePart = request.getPart("image");
            if (imagePart != null && imagePart.getSize() > 0) {
                InputStream inputStream = imagePart.getInputStream();
                byte[] imageBytes = new byte[(int) imagePart.getSize()];
                inputStream.read(imageBytes);
                product.setImage(imageBytes);
            } else {
                Product existingProduct = this.productService.getProductById(product.getProductId());
                product.setImage(existingProduct.getImage());
            }
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

        this.productService.updateProduct(product);

        request.getSession().setAttribute("message", "Product has been updated successfully!");

        response.sendRedirect(request.getContextPath() + "/admin/products");
    }

    private Converter getLocalDateConverter() {
        return new Converter() {
            @Override
            public <T> T convert(Class<T> type, Object value) {
                if (value instanceof String) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate localDate = LocalDate.parse((String) value, formatter);
                    return type.cast(localDate);
                } else {
                    throw new UnsupportedOperationException("Conversion from " + value.getClass() + " to " + type + " is not supported.");
                }
            }
        };
    }

    private void getImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String productId = request.getParameter("productId");
        Product product = productService.getProductById(Integer.parseInt(productId));

        response.setContentType("image/jpeg");
        response.getOutputStream().write(product.getImage());
    }
}
