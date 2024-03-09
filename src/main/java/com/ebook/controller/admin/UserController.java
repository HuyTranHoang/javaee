package com.ebook.controller.admin;

import com.ebook.entity.User;
import com.ebook.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

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
        ServletContext servletContext = getServletContext();
        userService = (UserService) servletContext.getAttribute("userService");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo() != null ? request.getPathInfo() : "/list-user";

        System.out.println("action in get: " + action);

        if (action.startsWith("/new")) {
            showCreateForm(request, response);
            return;
        } else if (action.startsWith("/edit/")) {
            showEditForm(request, response, action);
            return;
        }

        listUser(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo() != null ? request.getPathInfo() : "/list-user";

        System.out.println("action in post: " + action);

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
                listUser(request, response);
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

    private void showEditForm(HttpServletRequest request, HttpServletResponse response, String action) throws ServletException, IOException {
        String[] parts = action.split("/");

        if (parts.length == 3) {
            String userIdString = parts[2];
            int userId = Integer.parseInt(userIdString);
            User existingUser = this.userService.getUserById(userId);
            request.setAttribute("user", existingUser);
            request.setAttribute("mode", "edit");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user/user_form.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/users");
        }
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (this.userService.getUserByEmail(user.getEmail()) != null) {
            request.getSession().setAttribute("error", "User with email " + user.getEmail() + " already exists!");
            response.sendRedirect(request.getContextPath() + "/admin/users/new");
            return;
        }

        this.userService.insertUser(user);

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

        User existingUser = this.userService.getUserByEmail(user.getEmail());

        if (existingUser != null && existingUser.getUserId() != user.getUserId()) {
            request.getSession().setAttribute("error", "User with email " + user.getEmail() + " already exists!");
            response.sendRedirect(request.getContextPath() + "/admin/users/edit/" + user.getUserId());
            return;
        }

        this.userService.updateUser(user);

        request.getSession().setAttribute("message", "User has been updated successfully!");

        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}