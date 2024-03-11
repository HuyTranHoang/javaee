package com.ebook.controller.admin;

import com.ebook.entity.Category;
import com.ebook.service.CategoryService;
import org.apache.commons.beanutils.BeanUtils;
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

@WebServlet(name = "CategoryController", value = "/admin/categories/*")
public class CategoryController extends HttpServlet {

    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.cfg.xml");
        this.categoryService = context.getBean("categoryService", CategoryService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo() != null ? request.getPathInfo() : "/list-category";

        System.out.println("action in get: " + action);

        if (action.startsWith("/new")) {
            showCreateForm(request, response);
            return;
        } else if (action.startsWith("/edit/")) {
            showEditForm(request, response, action);
            return;
        }

        listCategory(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo() != null ? request.getPathInfo() : "/list-category";

        System.out.println("action in post: " + action);

        switch (action) {
            case "/insert":
                insertCategory(request, response);
                break;
            case "/delete":
                deleteCategory(request, response);
                break;
            case "/update":
                updateCategory(request, response);
                break;
            default:
                listCategory(request, response);
                break;
        }
    }

    private void listCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = this.categoryService.getAllCategories();
        request.setAttribute("categories", categories);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/category/category_list.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = new Category();
        request.setAttribute("category", category);
        request.setAttribute("mode", "create");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/category/category_form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response, String action) throws ServletException, IOException {
        String[] parts = action.split("/");

        if (parts.length == 3) {
            String categoryIdString = parts[2];
            int categoryId = Integer.parseInt(categoryIdString);
            Category existingCategory = this.categoryService.getCategoryById(categoryId);
            request.setAttribute("category", existingCategory);
            request.setAttribute("mode", "edit");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/category/category_form.jsp");
            dispatcher.forward(request, response);
        } else {
            System.out.println("Invalid URL");
            response.sendRedirect(request.getContextPath() + "/admin/categories");
        }
    }

    private void insertCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Category category = new Category();
        try {
            BeanUtils.populate(category, request.getParameterMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            this.categoryService.insertCategory(category);
        } catch (IllegalArgumentException e) {
            request.getSession().setAttribute("error", "Category with name " + category.getName() + " already exists!");
            response.sendRedirect(request.getContextPath() + "/admin/categories/new");
            return;
        }

        request.getSession().setAttribute("message", "New category has been added successfully!");

        response.sendRedirect(request.getContextPath() + "/admin/categories");
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("deleteCategoryId"));

        this.categoryService.deleteCategory(id);

        request.getSession().setAttribute("message", "Category has been deleted successfully!");

        response.sendRedirect(request.getContextPath() + "/admin/categories");
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Category category = new Category();

        try {
            BeanUtils.populate(category, request.getParameterMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            this.categoryService.updateCategory(category);
        } catch (IllegalArgumentException e) {
            request.getSession().setAttribute("error", "Category with name " + category.getName() + " already exists!");
            response.sendRedirect(request.getContextPath() + "/admin/categories/edit/" + category.getCategoryId());
            return;
        }

        request.getSession().setAttribute("message", "Category has been updated successfully!");

        response.sendRedirect(request.getContextPath() + "/admin/categories");
    }
}
