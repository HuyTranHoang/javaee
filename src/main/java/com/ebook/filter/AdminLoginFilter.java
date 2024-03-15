package com.ebook.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*"})
public class AdminLoginFilter extends HttpFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        boolean loggedIn = session != null && session.getAttribute("userEmail") != null;

        String loginURI = request.getContextPath() + "/admin/login";

        boolean loginRequest = request.getRequestURI().equals(loginURI);

        if (loggedIn || loginRequest) {
            chain.doFilter(req, res);
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/login");
        }
    }

    @Override
    public void destroy() {
        // Không cần thiết phải triển khai phương thức này, nhưng bạn có thể thực hiện các hủy bỏ cần thiết ở đây.
    }
}
