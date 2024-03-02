package com.ebook.controller.admin;

import com.ebook.entity.User;
import com.ebook.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserController", value = "/admin/users/*")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getServletPath();

        String action = request.getPathInfo() != null ? request.getPathInfo() : "/list-user";

        System.out.println("action in get: " + action);

        switch (action) {
            case "/new":
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user/user_form.jsp");
                dispatcher.forward(request, response);
                break;
//                case "/edit":
//                    showEditForm(request, response);
//                    break;
            default:
                listUser(request, response);
                break;
        }
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
//            case "/update":
//                updateUser(request, response);
//                break;
            default:
                listUser(request, response);
                break;
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();

        List<User> listUser = userService.getAllUsers();

        System.out.println("listUser: " + listUser);
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user/user_list.jsp");
        dispatcher.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");

        User user = new User();
        user.setEmail(email);
        user.setFullName(fullName);
        user.setPassword(password);

        System.out.println(user);

        UserService userService = new UserService();
        userService.insertUser(user);

        response.sendRedirect(request.getContextPath() + "/admin/users");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("deleteUserId"));

        UserService userService = new UserService();
        userService.deleteUser(id);

        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}