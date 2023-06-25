package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/user/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        try {
            String url = session.getAttribute("user") == null ? "/login.jsp" : "/user/hello.jsp";
            httpServletRequest.getServletContext().getRequestDispatcher(url).forward(request, response);

        } catch (NullPointerException e) {
            httpServletRequest.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}