package com.ebook.config;

import com.ebook.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        UserService userService = new UserService();
        servletContext.setAttribute("userService", userService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // clean up resources
    }
}