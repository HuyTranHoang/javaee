package com.ebook.filter;

import com.ebook.service.CategoryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class LoadCategoryFilter extends HttpFilter implements Filter {

    private CategoryService categoryService;
    @Override
    public void init(FilterConfig config) throws ServletException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.cfg.xml");
        this.categoryService = context.getBean("categoryService", CategoryService.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        if (uri.contains("/admin/") || uri.contains("/assets/") || uri.contains("/login") || uri.contains("/register") || uri.contains("/logout")) {
            filterChain.doFilter(request, response);
            return;
        }

        request.setAttribute("categories", categoryService.getAllCategories());
        filterChain.doFilter(request, response);
    }

}
