package com.ebook.filter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RootRedirectFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Không cần thiết phải triển khai phương thức này, nhưng bạn có thể thực hiện các khởi tạo cần thiết ở đây.
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Kiểm tra nếu đường dẫn là "/"
        if (request.getServletPath().equals("/")) {
            // Redirect đến "/index"
            response.sendRedirect(request.getContextPath() + "/index");
        } else {
            // Nếu không phải, tiếp tục chạy các Filter hoặc Servlet tiếp theo trong chuỗi Filter.
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Không cần thiết phải triển khai phương thức này, nhưng bạn có thể thực hiện các hủy bỏ cần thiết ở đây.
    }
}