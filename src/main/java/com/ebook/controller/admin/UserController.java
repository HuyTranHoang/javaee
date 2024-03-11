package com.ebook.controller.admin;

import com.ebook.entity.User;
import com.ebook.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserController", value = "/admin/users/*")
public class UserController extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.cfg.xml");
        this.userService = context.getBean("userService", UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            listUser(request, response);
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
                listUser(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo() != null ? request.getPathInfo() : "/invalid-action";

        switch (action) {
            case "/insert":
                insertUser(request, response);
                break;
            case "/delete":
                deleteUser(request, response);
                break;
            case "/update":
                updateUser(request, response);
                break;
            default:
                request.getSession().setAttribute("error", "Invalid action!");
                response.sendRedirect(request.getContextPath() + "/admin/users");
                break;
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchString = request.getParameter("searchString");

        List<User> listUser = this.userService.getAllUsers(searchString);
        request.setAttribute("listUser", listUser);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user/user_list.jsp");
        dispatcher.forward(request, response);
    }


    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        request.setAttribute("user", user);
        request.setAttribute("mode", "create");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user/user_form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response, String userIdString) throws ServletException, IOException {
        int userId = Integer.parseInt(userIdString);
        User existingUser = this.userService.getUserById(userId);

        if (existingUser == null) {
            request.getSession().setAttribute("error", "User with id " + userId + " does not exist!");
            response.sendRedirect(request.getContextPath() + "/admin/users");
            return;
        }

        request.setAttribute("user", existingUser);
        request.setAttribute("mode", "edit");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user/user_form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            this.userService.insertUser(user);
        } catch (IllegalArgumentException e) {
            request.getSession().setAttribute("error", "User with email " + user.getEmail() + " already exists!");
            response.sendRedirect(request.getContextPath() + "/admin/users/new");
            return;
        }

        request.getSession().setAttribute("message", "New user has been added successfully!");

        response.sendRedirect(request.getContextPath() + "/admin/users");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("deleteUserId"));

        this.userService.deleteUser(id);

        request.getSession().setAttribute("message", "User has been deleted successfully!");

        response.sendRedirect(request.getContextPath() + "/admin/users");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = new User();

        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            this.userService.updateUser(user);
        } catch (IllegalArgumentException e) {
            request.getSession().setAttribute("error", "User with email " + user.getEmail() + " already exists!");
            response.sendRedirect(request.getContextPath() + "/admin/users/edit/" + user.getUserId());
            return;
        }

        request.getSession().setAttribute("message", "User has been updated successfully!");

        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}