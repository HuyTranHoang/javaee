package com.ebook.controller.admin;

import com.ebook.service.UserService;
import com.ebook.utils.EncryptUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/admin/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.cfg.xml");
        this.userService = context.getBean("userService", UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (userService.authenticateUser(email, password)) {
            System.out.println("Login successful");
            request.getSession().setAttribute("userEmail", email);
            response.sendRedirect(request.getContextPath() + "/admin/");
        } else {
            System.out.println("Login failed");
            request.getSession().setAttribute("error", "Invalid email or password");
            response.sendRedirect(request.getContextPath() + "/admin/login");
        }
    }
}
